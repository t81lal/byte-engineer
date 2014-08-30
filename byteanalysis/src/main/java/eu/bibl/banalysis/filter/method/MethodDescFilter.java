package eu.bibl.banalysis.filter.method;

import eu.bibl.banalysis.filter.MethodFilter;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sc4re
 */
public class MethodDescFilter implements MethodFilter {
    protected final List<String> descs;

    public MethodDescFilter(String...names) {
        this.descs = new ArrayList<>();
        this.descs.addAll(Arrays.asList(names));
    }

    @Override
    public boolean accept(MethodNode methodNode) {
        return descs.contains(methodNode.desc);
    }
}
