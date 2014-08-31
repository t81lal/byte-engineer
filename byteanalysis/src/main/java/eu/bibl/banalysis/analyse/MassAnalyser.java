package eu.bibl.banalysis.analyse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import eu.bibl.banalysis.asm.ClassNode;
import eu.bibl.banalysis.storage.HookMap;
import eu.bibl.banalysis.storage.classes.ClassContainer;

/**
 * @author sc4re
 */
public class MassAnalyser {
	
	protected ClassContainer classContainer;
	protected List<Analyser> singleAnalysers;
	
	public MassAnalyser() {
		singleAnalysers = new ArrayList<>();
	}
	
	public void add(Analyser... analysers) {
		singleAnalysers.addAll(Arrays.asList(analysers));
	}
	
	public void runSingleAnalysers(ClassContainer container, HookMap mapping) {
		List<Analyser> unanalysed = new ArrayList<>();
		LinkedList<Analyser> analysed = new LinkedList<>();
		for (Analyser an : singleAnalysers) {
			unanalysed.add(an);
		}
		int iterationsSpentAtThisSize = 0;
		int lastSize = 0;
		while (true) {
			for (ClassNode cn : container.getNodes().values()) {
				for (Analyser an : unanalysed) {
					an.run(cn);
					if (an.isAnalysed())
						analysed.add(an);
				}
			}
			while (!analysed.isEmpty()) {
				Analyser sa = analysed.pop();
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
	}
}
