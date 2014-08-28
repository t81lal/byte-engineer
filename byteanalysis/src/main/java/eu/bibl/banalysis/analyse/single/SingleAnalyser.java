package eu.bibl.banalysis.analyse.single;

import eu.bibl.banalysis.analyse.AnalysisTracker;
import eu.bibl.banalysis.analyse.stats.AnalysisStatistics;
import eu.bibl.banalysis.asm.ClassNode;
import eu.bibl.banalysis.storage.HookMap;
import eu.bibl.banalysis.storage.classes.ClassContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sc4re
 */
public abstract class SingleAnalyser implements AnalysisTracker {
    /** Sub-analysers that are to be run after this one is **/
    protected final List<SubSingleAnalyser> subAnalysers;

    private boolean analysed;

    protected ClassNode cn;
    protected HookMap mapping;

    protected final AnalysisStatistics stats;

    public SingleAnalyser() {
        subAnalysers = new ArrayList<>();
        analysed = false;
        cn = null;
        mapping = null;
        stats = new AnalysisStatistics();
    }

    @Override
    public AnalysisStatistics getStats() {
        return stats;
    }

    public void run(ClassContainer container, ClassNode cn, HookMap mapping) {
        if (accept(container, cn, mapping)) {
            this.cn = cn;
            this.mapping = mapping;
            call(container);
        }
    }

    public boolean isAnalysed() {
        return analysed;
    }

    public abstract boolean accept(ClassContainer container, ClassNode cn, HookMap mapping);

    public abstract void analyse(ClassContainer container);

    private void call(ClassContainer container) {
        analyse(container);
        callSubs(container);
        analysed = true;
    }

    private void callSubs(ClassContainer container) {
        for (SubSingleAnalyser sa : subAnalysers) {
            sa.run(container, cn, mapping);
        }
    }
}
