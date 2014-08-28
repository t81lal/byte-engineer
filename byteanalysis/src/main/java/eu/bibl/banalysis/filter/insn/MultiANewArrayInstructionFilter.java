package eu.bibl.banalysis.filter.insn;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MultiANewArrayInsnNode;

import eu.bibl.banalysis.filter.ConstantFilter;
import eu.bibl.banalysis.filter.InstructionFilter;
import eu.bibl.banalysis.filter.IntegerFilter;
import eu.bibl.banalysis.filter.ZeroCancelIntegerFilter;

public class MultiANewArrayInstructionFilter implements InstructionFilter {
	
	protected ConstantFilter<String> descFilter;
	protected IntegerFilter dimsFilter;
	
	public MultiANewArrayInstructionFilter(String desc, int dims) {
		descFilter = new ConstantFilter<String>(desc);
		dimsFilter = new ZeroCancelIntegerFilter(dims);
	}
	
	@Override
	public boolean accept(AbstractInsnNode t) {
		if (!(t instanceof MultiANewArrayInsnNode))
			return false;
		return descFilter.accept(((MultiANewArrayInsnNode) t).desc) && dimsFilter.accept(((MultiANewArrayInsnNode) t).dims);
	}
}