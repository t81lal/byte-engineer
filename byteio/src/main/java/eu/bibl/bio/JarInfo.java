package eu.bibl.bio;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarFile;

/**
 * Holds information about a single local or external JarFile.
 * @author Bibl
 */
public class JarInfo {

	public static boolean VERBOSE_WARNINGS = false;

	private String path;
	private JarType type;
	private JarURLConnection connection;

	/**
	 * Creates a new holder as the JarFile is on the local system.
	 * @param path Path to jar.
	 */
	public JarInfo(File path) {
		this(path.getAbsolutePath(), JarType.FILE);
	}

	/**
	 * Creates a new holder.
	 * @param path Path to jar.
	 * @param type Type of jar.
	 */
	public JarInfo(String path, JarType type) {
		this.path = path;
		this.type = type;
	}

	/**
	 * Sets up the connection to the JarFile.
	 * @return True for success else false.
	 */
	public boolean setupConnection() {
		if (connection != null) {
			throw new UnsupportedOperationException("Cannot reconnect connection.");
		}
		URL jarURL = null;
		try {
			jarURL = new URL(createJarURLCProtocolLocation(path, type));
		} catch (MalformedURLException e) {
			jarURL = null;
		}
		if (jarURL == null)
			return false;
		try {
			JarURLConnection connection = (JarURLConnection) jarURL.openConnection();
			this.connection = connection;
			return true;
		} catch (IOException e) {
			if (VERBOSE_WARNINGS)
				e.printStackTrace();
			connection = null;
			return false;
		}
	}

	/**
	 * @return The {@link JarURLConnection} to the jar.
	 */
	public JarURLConnection getConnection() {
		return connection;
	}

	/**
	 * @return {@link URL} to the JarFile.
	 */
	public URL getJarURL() {
		return connection.getJarFileURL();
	}

	/**
	 * @return Real path to JarFile.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return The actual {@link JarFile} object from the connection.
	 */
	public JarFile getJarFile() {
		try {
			return connection.getJarFile();
		} catch (IOException e) {
			if (VERBOSE_WARNINGS)
				e.printStackTrace();
			return null;
		}
	}

	/**
	 * Formats a string ready for a {@link JarURLConnection} to connect to.
	 * @param url Location of the JarFile.
	 * @param type Type of JarFile.
	 * @return The formatted string.
	 */
	public static String createJarURLCProtocolLocation(String url, JarType type) {
		String jarPath = "jar:";
		jarPath += JarType.prefix(type);
		jarPath += url;
		if (type.equals(JarType.FILE) && !url.endsWith(".jar")) {
			File file = new File(url);
			if (!file.exists())
				jarPath += ".jar";
		}
		jarPath += "!/";
		return jarPath;
	}
}