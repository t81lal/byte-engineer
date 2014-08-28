package eu.bibl.bio.jfile.classloader;

import java.net.URL;
import java.util.HashMap;
import java.util.Set;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import sun.misc.URLClassPath;
import eu.bibl.banalysis.storage.classes.ClassContainer;

/**
 * Specific {@link ClassLoader} for loading things from external JarFiles, caching loaded classes as it goes along. <br>
 *
 * @author Bibl
 */
public class JarClassLoader extends ClassLoader {

	/** Associated path for classes **/
	protected ClassContainer container;
	/** Previously loaded classes **/
	protected HashMap<String, Class<?>> cache;
	/** Resource class path **/
	protected URLClassPath ucp;

	/**
	 * @param contents Reference to the JarContents object.
	 */
	public JarClassLoader(ClassContainer container) {
		this.container = container;
		cache = new HashMap<String, Class<?>>();
		Set<URL> urlSet = container.resources.keySet();
		URL[] urls = urlSet.toArray(new URL[urlSet.size()]);
		ucp = new URLClassPath(urls);
	}

	/**
	 * Add a resource URL to the class path by force.
	 * @param url URL to add.
	 */
	public void addJar(URL url) {
		ucp.addURL(url);
	}

	/**
	 * Adds the class contents of the {@link ClassContainer} as well as the resource locations.
	 * @param contents Contents to add.
	 */
	public void addJar(ClassContainer container) {
		this.container.add(container);
		for (URL url : container.resources.keySet())
			ucp.addURL(url);
	}

	@Override
	public URL findResource(final String name) {
		return ucp.findResource(name, true);
	}

	/**
	 * @param cn ClassNode to define
	 * @return Defined Class.
	 */
	public Class<?> defineNode(ClassNode cn) {
		ClassWriter cw = new ClassWriter(0);
		return defineNode(cn, cw);
	}

	/**
	 * Defines a ClassNode with a custom {@link ClassWriter}
	 *
	 * @param cn ClassNode to define
	 * @param cw ClassWriter to define with
	 * @return Defined Class.
	 */
	public Class<?> defineNode(ClassNode cn, ClassWriter cw) {
		cn.accept(cw);
		byte[] bytes = cw.toByteArray();
		Class<?> c = defineClass(null, bytes, 0, bytes.length);
		if (c != null)
			cache.put(cn.name, c);
		return c;
	}

	// @Override
	// public Class<?> loadClass(String name) throws ClassNotFoundException {
	// return findClass(name.replace(".", "/"));
	// }

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		name = name.replace(".", "/");
		if (cache.containsKey(name))
			return cache.get(name);
		ClassNode node = container.getNodes().get(name);
		if (node != null)
			return defineNode(node);
		// Class<?> c = super.loadClass(name);
		// if (c != null) {
		// cacheClass(name, c);
		// return c;
		// }
		Class<?> c = getSystemClassLoader().loadClass(name);
		if (c != null)
			cacheClass(name, c);
		return c;
	}

	/**
	 * Forces the class to be put into the cache overwriting the previously loaded one.
	 * @param name Fully qualified name of the class.
	 * @param c Class to add.
	 */
	public void cacheClass(String name, Class<?> c) {
		if ((c != null) && !c.getName().startsWith("java"))
			cache.put(name, c);
	}
}