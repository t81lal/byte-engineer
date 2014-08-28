package eu.bibl.banalysis.analyse.multi;

import eu.bibl.banalysis.asm.ClassNode;
import eu.bibl.banalysis.storage.HookMap;
import eu.bibl.banalysis.storage.classes.ClassContainer;

import java.util.Collection;

/**
 * @author sc4re
 */
public abstract class SubMultiAnalyser {

    protected final MultiAnalyser base;

    public SubMultiAnalyser(MultiAnalyser base) {
        this.base = base;
    }

    public abstract void analyse(ClassContainer container, Collection<ClassNode> toAnalyse, HookMap mapping);
}
