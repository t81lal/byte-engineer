package eu.bibl.banalysis.filter.insn;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

import eu.bibl.banalysis.filter.ConstantFilter;
import eu.bibl.banalysis.filter.OpcodeFilter;

public class TypeInstructionFilter extends OpcodeInstructionFilter {
	
	private ConstantFilter<String> descFilter;
	
	public TypeInstructionFilter(OpcodeFilter filter, String desc) {
		super(filter);
		descFilter = new ConstantFilter<String>(desc);
	}
	
	public TypeInstructionFilter(int opcode, String desc) {
		super(new OpcodeFilter(opcode));
		descFilter = new ConstantFilter<String>(desc);
	}
	
	@Override
	public boolean accept(AbstractInsnNode t) {
		if (!(t instanceof TypeInsnNode))
			return false;
		TypeInsnNode tin = (TypeInsnNode) t;
		return opcodeFilter.accept(tin) && descFilter.accept(tin.desc);
	}
}