package eu.bibl.banalysis.asm;

import java.util.List;

import org.objectweb.asm.tree.FieldNode;

import eu.bibl.banalysis.filter.Filter;

public class FieldVector extends InfoVector<FieldNode> {
	
	public FieldVector(List<FieldNode> fields) {
		super(fields);
	}
	
	public FieldVector(List<FieldNode> fields, boolean definiteCount, Filter<FieldNode> filter) {
		super(fields, definiteCount, filter);
	}
}