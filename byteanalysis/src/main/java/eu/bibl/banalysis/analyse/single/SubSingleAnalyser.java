package eu.bibl.banalysis.analyse.single;

import eu.bibl.banalysis.asm.ClassNode;
import eu.bibl.banalysis.storage.HookMap;
import eu.bibl.banalysis.storage.classes.ClassContainer;

/**
 * @author sc4re
 */
public abstract class SubSingleAnalyser {

    protected final SingleAnalyser base;

    public SubSingleAnalyser(SingleAnalyser base) {
        this.base = base;
    }

    public abstract void run(ClassContainer container, ClassNode base, HookMap hm);
}
