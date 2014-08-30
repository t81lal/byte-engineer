package eu.bibl.banalysis.filter.field;

import eu.bibl.banalysis.filter.FieldFilter;
import org.objectweb.asm.tree.FieldNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sc4re
 */
public class FieldNameFilter implements FieldFilter {
    protected final List<String> names;

    public FieldNameFilter(String...names) {
        this.names = new ArrayList<>();
        this.names.addAll(Arrays.asList(names));
    }

    @Override
    public boolean accept(FieldNode fieldNode) {
        return names.contains(fieldNode.name);
    }
}
