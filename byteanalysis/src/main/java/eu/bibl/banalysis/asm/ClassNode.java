package eu.bibl.banalysis.asm;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import eu.bibl.banalysis.filter.FieldFilter;
import eu.bibl.banalysis.filter.MethodFilter;

@SuppressWarnings("all")
public class ClassNode extends org.objectweb.asm.tree.ClassNode {
	
	public ClassNode() {
		super(Opcodes.ASM5);// don't super() it will throw an illegalstateexception
	}
	
	public ClassNode(int asmVersion) {
		super(asmVersion);
	}
	
	public List<MethodNode> methods() {
		return methods;
	}
	
	public List<MethodNode> methods(MethodFilter filter) {
		List<MethodNode> methods = new ArrayList<MethodNode>();
		for (MethodNode method : methods()) {
			if (filter.accept(method))
				methods.add(method);
		}
		return methods;
	}
	
	public List<FieldNode> fields() {
		return fields;
	}
	
	public List<FieldNode> fields(FieldFilter filter) {
		List<FieldNode> fields = new ArrayList<FieldNode>();
		for (FieldNode field : fields()) {
			if (filter.accept(field))
				fields.add(field);
		}
		return fields;
	}
}