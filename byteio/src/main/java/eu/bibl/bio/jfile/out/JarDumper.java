package eu.bibl.bio.jfile.out;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarOutputStream;

import org.objectweb.asm.tree.ClassNode;

public interface JarDumper {
	
	public void dump(File file);
	
	public int dumpClass(JarOutputStream out, String name, ClassNode cn) throws IOException;
	
	public int dumpResource(JarOutputStream out, String name, byte[] file) throws IOException;
}