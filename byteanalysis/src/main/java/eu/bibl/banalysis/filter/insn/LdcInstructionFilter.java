package eu.bibl.banalysis.filter.insn;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;

import eu.bibl.banalysis.filter.ConstantFilter;
import eu.bibl.banalysis.filter.InstructionFilter;

public class LdcInstructionFilter implements InstructionFilter {
	
	private ConstantFilter<Object> filter;
	
	public LdcInstructionFilter(Object cst) {
		if (cst == null)
			filter = new ConstantFilter<Object>();
		else
			filter = new ConstantFilter<Object>(cst);
	}
	
	@Override
	public boolean accept(AbstractInsnNode t) {
		if (!(t instanceof LdcInsnNode))
			return false;
		LdcInsnNode ldc = (LdcInsnNode) t;
		return filter.accept(ldc.cst);
	}
}