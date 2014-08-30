package eu.bibl.banalysis.filter.method;

import eu.bibl.banalysis.filter.MethodFilter;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sc4re
 */
public class MethodNameFilter implements MethodFilter {

    protected final List<String> names;

    public MethodNameFilter(String...names) {
        this.names = new ArrayList<>();
        this.names.addAll(Arrays.asList(names));
    }

    @Override
    public boolean accept(MethodNode methodNode) {
       return names.contains(methodNode.name);
    }
}
