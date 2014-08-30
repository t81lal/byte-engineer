package eu.bibl.banalysis.filter.field;

import eu.bibl.banalysis.filter.FieldFilter;
import org.objectweb.asm.tree.FieldNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sc4re
 */
public class FieldDescFilter implements FieldFilter {
    protected final List<String> descs;

    public FieldDescFilter(String...names) {
        this.descs = new ArrayList<>();
        this.descs.addAll(Arrays.asList(names));
    }

    @Override
    public boolean accept(FieldNode fieldNode) {
        return descs.contains(fieldNode.desc);
    }
}
