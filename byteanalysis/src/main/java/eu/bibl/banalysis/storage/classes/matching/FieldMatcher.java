package eu.bibl.banalysis.storage.classes.matching;

import eu.bibl.banalysis.asm.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import java.util.List;

/**
 * @author sc4re
 */
public interface FieldMatcher extends ContainerMatcher<ClassNode, List<FieldNode>> {

}
