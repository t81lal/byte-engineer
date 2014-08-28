package eu.bibl.banalysis.analyse;

import eu.bibl.banalysis.analyse.multi.MultiAnalyser;
import eu.bibl.banalysis.analyse.single.SingleAnalyser;
import eu.bibl.banalysis.analyse.stats.AnalysisStatistics;
import eu.bibl.banalysis.asm.ClassNode;
import eu.bibl.banalysis.storage.HookMap;
import eu.bibl.banalysis.storage.classes.ClassContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author sc4re
 */
public class MassAnalyser {
    protected List<MultiAnalyser> multiAnalysers;
    protected List<SingleAnalyser> singleAnalysers;

    public MassAnalyser() {
        this.multiAnalysers = new ArrayList<>();
        this.singleAnalysers = new ArrayList<>();
    }

    public void addMulti(MultiAnalyser ... analysers) {
        this.multiAnalysers.addAll(Arrays.asList(analysers));
    }

    public void addSingle(SingleAnalyser analysers) {
        this.singleAnalysers.addAll(Arrays.asList(analysers));
    }

    public AnalysisStatistics runSingleAnalysers(ClassContainer container, HookMap mapping) {
        AnalysisStatistics stats = new AnalysisStatistics();
        List<SingleAnalyser> unanalysed = new ArrayList<>();
        LinkedList<SingleAnalyser> analysed = new LinkedList<>();
        for (SingleAnalyser an : this.singleAnalysers) {
            unanalysed.add(an);
        }
        int iterationsSpentAtThisSize = 0;
        int lastSize = 0;
        while (true) {
            for (ClassNode cn : container.getNodes().values()) {
                for (SingleAnalyser an : unanalysed) {
                    an.run(container, cn, mapping);
                    if (an.isAnalysed())
                        analysed.add(an);
                }
            }
            while (!analysed.isEmpty()) {
                SingleAnalyser sa = analysed.pop();
                stats = stats.combine(sa.getStats());
                unanalysed.remove(sa);
            }
            if (lastSize == unanalysed.size())
                iterationsSpentAtThisSize++;
            else
                iterationsSpentAtThisSize = 0;
            if (iterationsSpentAtThisSize >= 2)
                break;
            if (unanalysed.size() == 0)
                break;
            lastSize = unanalysed.size();
        }
        return stats;
    }

    public AnalysisStatistics runMultiAnalysers(ClassContainer container, HookMap mapping) {
        AnalysisStatistics stats = new AnalysisStatistics();
        List<MultiAnalyser> unanalysed = new ArrayList<>();
        LinkedList<MultiAnalyser> analysed = new LinkedList<>();
        for (MultiAnalyser an : this.multiAnalysers) {
            unanalysed.add(an);
        }
        int iterationsSpentAtThisSize = 0;
        int lastSize = 0;
        while (true) {
            for (MultiAnalyser an : unanalysed) {
                an.run(container, mapping);
                if (an.isAnalysed())
                    analysed.add(an);
            }
            while (!analysed.isEmpty()) {
                MultiAnalyser sa = analysed.pop();
                stats = stats.combine(sa.getStats());
                unanalysed.remove(sa);
            }
            if (lastSize == unanalysed.size())
                iterationsSpentAtThisSize++;
            else
                iterationsSpentAtThisSize = 0;
            if (iterationsSpentAtThisSize >= 2)
                break;
            if (unanalysed.size() == 0)
                break;
            lastSize = unanalysed.size();
        }
        return stats;
    }
}
