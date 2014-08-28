package eu.bibl.bio.jfile.out;

import eu.bibl.banalysis.storage.classes.ClassContainer;

import java.io.IOException;
import java.util.jar.JarOutputStream;

public class NonMetaJarDumper extends CompleteJarDumper {
	
	public NonMetaJarDumper(ClassContainer contents) {
		super(contents);
	}
	
	@Override
	public int dumpResource(JarOutputStream out, String name, byte[] file) throws IOException {
		if (name.contains("META-INF"))
			return 0;
		return super.dumpResource(out, name, file);
	}
}