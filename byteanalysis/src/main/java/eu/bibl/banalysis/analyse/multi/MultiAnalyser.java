package eu.bibl.banalysis.analyse.multi;

import eu.bibl.banalysis.analyse.AnalysisTracker;
import eu.bibl.banalysis.analyse.stats.AnalysisStatistics;
import eu.bibl.banalysis.asm.ClassNode;
import eu.bibl.banalysis.storage.HookMap;
import eu.bibl.banalysis.storage.classes.ClassContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author sc4re
 */
public abstract class MultiAnalyser implements AnalysisTracker {
    protected final AnalysisStatistics stats;
    protected final List<SubMultiAnalyser> subAnalysers;

    protected Collection<ClassNode> targets;
    protected HookMap mapping;

    private boolean analysed;

    public MultiAnalyser() {
        this.stats = new AnalysisStatistics();
        subAnalysers = new ArrayList<>();
        analysed = false;
    }

    public void run(ClassContainer container, HookMap mapping) {
        Collection<ClassNode> tgts = getAnalysisTargets(container, mapping);
        if (tgts == null)
            return;
        if (tgts.size() == 0)
            return;
        this.targets = tgts;
        this.mapping = mapping;
        call(container);
    }

    public abstract Collection<ClassNode> getAnalysisTargets(ClassContainer container, HookMap mapping);

    public abstract void analyse(ClassContainer container);

    public boolean isAnalysed() {
        return analysed;
    }

    private void call(ClassContainer container) {
        analyse(container);
        callSubs(container);
        analysed = true;
    }

    private void callSubs(ClassContainer container) {
        for (SubMultiAnalyser sma : this.subAnalysers) {
            sma.analyse(container, this.targets, this.mapping);
        }
    }

    @Override
    public AnalysisStatistics getStats() {
        return stats;
    }
}
