package eu.bibl.banalysis.filter.insn;

import org.objectweb.asm.tree.AbstractInsnNode;

import eu.bibl.banalysis.filter.InstructionFilter;
import eu.bibl.banalysis.filter.OpcodeFilter;

public abstract class OpcodeInstructionFilter implements InstructionFilter {
	
	protected OpcodeFilter opcodeFilter;
	
	public OpcodeInstructionFilter(OpcodeFilter opcodeFilter) {
		this.opcodeFilter = opcodeFilter;
	}
	
	@Override
	public abstract boolean accept(AbstractInsnNode t);
}