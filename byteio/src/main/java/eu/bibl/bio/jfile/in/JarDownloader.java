package eu.bibl.bio.jfile.in;

import eu.bibl.banalysis.asm.ClassNode;
import eu.bibl.banalysis.storage.classes.ClassContainer;
import eu.bibl.bio.JarInfo;
import eu.bibl.bio.jfile.classloader.JarClassLoader;
import org.objectweb.asm.ClassReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarDownloader {

	/** Whether or not to print out warnings (default=true). **/
	public static boolean VERBOSE_WARNINGS = true;

	protected JarInfo[] jars;
	protected ClassContainer contents;
	protected JarClassLoader classLoader;

	/**
	 * Creates a new JarDownloader without parsing.
	 * @param jars JarFile informations.
	 */
	public JarDownloader(JarInfo... jars) {
		super();
		this.jars = jars;
		contents = new ClassContainer();
		classLoader = new JarClassLoader(contents);
	}

	/**
	 * Parses all the JarFiles and stores the contents.
	 * @param print Whether to print the results.
	 * @return True if succeeded else false.
	 */
	public boolean parse(boolean print) {
		for(JarInfo info : jars) {
			if (!info.setupConnection())
				return false;
			if (!parse(info, print))
				return false;
		}
		return true;
	}

	/**
	 * Parses all the JarFiles and stores the contents.
	 * @return True if succeeded else false.
	 */
	public boolean parse() {
		boolean failed = true;
		for(JarInfo info : jars) {
			if (!info.setupConnection())
				failed = false;
			if (!parse(info, true))
				failed = false;
		}
		return failed;
	}

	/**
	 * Parses a JarFile and stores the contents.
	 * @param info JarFile information
	 * @param print Whether to print the results.
	 * @return True if succeeded else false.
	 */
	public boolean parse(JarInfo info, boolean print) {
		if (info == null)
			return false;
		JarURLConnection connection = info.getConnection();
		if (connection == null)
			return false;
		try {
			classLoader.addJar(info.getJarURL());
			JarFile jarFile = connection.getJarFile();
			Enumeration<JarEntry> entries = jarFile.entries();
			int startClassSize = contents.getNodes().size();
			int resCount = 0;
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				byte[] bytes = read(jarFile.getInputStream(entry));
				if (entry.getName().endsWith(".class")) {
					ClassReader cr = new ClassReader(bytes);
					ClassNode cn = new ClassNode();
					cr.accept(cn, 0);
					contents.addClass(cn);
				} else {
					if (bytes.length > 0) {
						if (bytes.length >= 4) {
							byte ch1 = bytes[0];
							byte ch2 = bytes[1];
							byte ch3 = bytes[2];
							byte ch4 = bytes[3];
							int magic = ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
							if (magic == 0xCAFEBABE) {
								ClassReader cr = new ClassReader(bytes);
								ClassNode cn = new ClassNode();
								cr.accept(cn, 0);
								contents.addClass(cn);
							} else {
								addResource(connection.getJarFileURL(), entry.getName(), bytes);
								resCount++;
							}
						} else {
							addResource(connection.getJarFileURL(), entry.getName(), bytes);
							resCount++;
						}
					} else {
						addResource(connection.getJarFileURL(), entry.getName(), bytes);
						resCount++;
					}
				}
			}
			if (print)
				System.out.println("Parsed jar with " + (contents.getNodes().size() - startClassSize) + " classes and " + resCount + " resources from " + info.getPath());
			return true;
		} catch (Exception e) {
			if (VERBOSE_WARNINGS)
				e.printStackTrace();
			return false;
		}
	}

	/**
	 * Adds a jarfile resource to the cache.
	 * @param jarUrl URL of the {@link JarFile}
	 * @param name Name of the resource
	 * @param fileBytes Resource in bytes.
	 */
	public void addResource(URL jarUrl, String name, byte[] fileBytes) {
		if (contents.resources.containsKey(jarUrl)) {
			contents.resources.get(jarUrl).put(name, fileBytes);
		} else {
			HashMap<String, byte[]> res = new HashMap<String, byte[]>();
			res.put(name, fileBytes);
			contents.resources.put(jarUrl, res);
		}
	}

	/**
	 * @return Contents of the JarFile.
	 */
	public ClassContainer getContents() {
		return contents;
	}

	/**
	 * @return The jar loading specific {@link ClassLoader}
	 */
	public JarClassLoader getClassLoader() {
		return classLoader;
	}

	/**
	 * Utility function to read a byte[] full from an InputStream. <br>
	 * Utility method that I didn't think should be on it's own in a helper class.<br>
	 *
	 * @param in InputStream that has the information.
	 * @return The contents of the stream as a byte[].
	 * @throws IOException If the stream cannot be read properly or is invalid.
	 */
	public static byte[] read(InputStream in) throws IOException {
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = in.read(buffer)) != -1)
			byteArrayOut.write(buffer, 0, bytesRead);
		byteArrayOut.close();
		return byteArrayOut.toByteArray();
	}
}