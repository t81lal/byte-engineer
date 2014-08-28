package eu.bibl.banalysis.storage.classes.matching;

import eu.bibl.banalysis.asm.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

/**
 * @author sc4re
 */
public interface MethodMatcher extends ContainerMatcher<ClassNode, List<MethodNode>> {

}
