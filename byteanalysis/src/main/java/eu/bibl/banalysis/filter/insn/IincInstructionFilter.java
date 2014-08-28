package eu.bibl.banalysis.filter.insn;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.IincInsnNode;

import eu.bibl.banalysis.filter.InstructionFilter;
import eu.bibl.banalysis.filter.IntegerFilter;
import eu.bibl.banalysis.filter.ZeroCancelIntegerFilter;

public class IincInstructionFilter implements InstructionFilter {
	
	protected IntegerFilter incFilter;
	protected IntegerFilter varFilter;
	
	public IincInstructionFilter(int inc, int var) {
		incFilter = new ZeroCancelIntegerFilter(inc);
		varFilter = new ZeroCancelIntegerFilter(var);
	}
	
	@Override
	public boolean accept(AbstractInsnNode t) {
		if (!(t instanceof IincInsnNode))
			return false;
		return incFilter.accept(((IincInsnNode) t).incr) && varFilter.accept(((IincInsnNode) t).var);
	}
}