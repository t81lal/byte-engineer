package eu.bibl.banalysis.storage.classes;

import eu.bibl.banalysis.asm.ClassNode;
import eu.bibl.banalysis.asm.refactor.Refactorer;
import eu.bibl.banalysis.storage.HookMap;
import eu.bibl.banalysis.storage.classes.matching.ClassMatcher;
import eu.bibl.banalysis.storage.classes.matching.FieldMatcher;
import eu.bibl.banalysis.storage.classes.matching.MethodMatcher;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.net.URL;
import java.util.*;

/**
 * @author sc4re
 */
public class ClassContainer {

    /** The classnodes with package+name -> node naming **/
    protected final Map<String, ClassNode> nodeMap;
    public final Map<URL, Map<String, byte[]>> resources;

    public ClassContainer() {
        nodeMap = new HashMap<>();
        resources = new HashMap<>();
    }

    public ClassContainer(Collection<ClassNode> nodes) {
        this();
        for (ClassNode cn : nodes) {
            nodeMap.put(cn.name, cn);
        }
    }

    public ClassContainer(ClassNode[] nodes) {
        this(Arrays.asList(nodes));
    }

    public final Map<String, ClassNode> getNodes() {
        return Collections.unmodifiableMap(nodeMap);
    }

    public void refactor(HookMap hm) {
        Refactorer rf = new Refactorer(hm, this);
        rf.run();
    }

    public void relocate(String oldName, ClassNode cn) {
        if (nodeMap.containsKey(oldName))
            nodeMap.remove(oldName);
        nodeMap.put(cn.name, cn);
    }

    public void add(ClassContainer other) {
        if (other == null)
            return;
        nodeMap.putAll(other.getNodes());
        resources.putAll(other.resources);
    }

    public void addClass(ClassNode cn) {
        nodeMap.put(cn.name, cn);
    }

    public List<ClassNode> retrieveClasses(ClassMatcher matcher) {
        if (matcher == null)
            return new ArrayList<>();
        return matcher.match(nodeMap.values());
    }

    public Map<ClassNode, List<MethodNode>> retrieveMatchingMethods(ClassMatcher cMatch, MethodMatcher mMatch) {
        List<ClassNode> klasses = retrieveClasses(cMatch);
        if (klasses.size() == 0)
            return new HashMap<>();
        Map<ClassNode, List<MethodNode>> result = new HashMap<>();
        for (ClassNode cn : klasses) {
            result.put(cn, mMatch.match(cn));
        }
        return result;
    }

    public Map<ClassNode, List<FieldNode>> retrieveMatchingFields(ClassMatcher cMatch, FieldMatcher fMatch) {
        List<ClassNode> klasses = retrieveClasses(cMatch);
        if (klasses.size() == 0)
            return new HashMap<>();
        Map<ClassNode, List<FieldNode>> result = new HashMap<>();
        for (ClassNode cn : klasses) {
            result.put(cn, fMatch.match(cn));
        }
        return result;
    }
}
