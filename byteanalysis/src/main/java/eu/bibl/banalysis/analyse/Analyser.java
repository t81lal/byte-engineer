package eu.bibl.banalysis.analyse;

import eu.bibl.banalysis.asm.ClassNode;
import eu.bibl.banalysis.storage.CallbackMappingData;
import eu.bibl.banalysis.storage.FieldMappingData;
import eu.bibl.banalysis.storage.HookMap;
import eu.bibl.banalysis.storage.classes.ClassContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sc4re
 */
public abstract class Analyser {
    /** The name of the analyser **/
    protected final String name;
    /** The field data we need to identify **/
    protected final List<FieldMappingData> fieldHooks;
    /** The method data we need to identify **/
    protected final List<CallbackMappingData> methodHooks;

    /** The ClassNode we are currently identifying **/
    protected ClassNode cn;
    /** The HookMap we are populating **/
    protected HookMap mapping;

    public Analyser(String name) {
        this.name = name;
        fieldHooks = new ArrayList<>();
        methodHooks = new ArrayList<>();
    }

    public void run(ClassContainer container, ClassNode cn, HookMap mapping) {
        if (accept(container, cn, mapping)) {
            this.cn = cn;
            this.mapping = mapping;
            run(container);
        }
    }

    /**
     * Checks if this ClassNode should be analysed by this analyser.
     * @param container The container of classes (used for certain analysers)
     * @param cn The ClassNode to check
     * @param mapping
     * @return True if the ClassNode is to be run on, False otherwise.
     */
    public abstract boolean accept(ClassContainer container, ClassNode cn, HookMap mapping);

    /**
     * Runs the analyser on the given ClassNode.
     * @param container The container of classes (used for certain analysers)
     */
    public abstract void run(ClassContainer container);

    /**
     * Returns whether the analyser is successfully identified.
     * @return True if the analyser is successfully identified, false otherwise.
     */
    public boolean isAnalysed() {
        for (FieldMappingData f : fieldHooks) {
            if (!f.isIdentified())
                return false;
        }
        for (CallbackMappingData m : methodHooks) {
            if (!m.isIdentified())
                return false;
        }
        return true;
    }
}
