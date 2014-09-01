package eu.bibl.banalysis.analyse;

import java.util.HashMap;
import java.util.Map;

public class AnalyserCache {
	
	public static final AnalyserCache GLOBAL_CACHE = new AnalyserCache();
	private static AnalyserCache currentContext = new AnalyserCache();
	
	protected Map<String, Analyser> cache;
	
	public AnalyserCache() {
		cache = new HashMap<String, Analyser>();
	}
	
	public Analyser get(String name) {
		return cache.get(name);
	}
	
	public void cache(String name, Analyser analyser) {
		cache.put(name, analyser);
	}
	
	public void cache(Analyser analyser) {
		cache.put(analyser.getName(), analyser);
	}
	
	public static AnalyserCache currentContext() {
		return currentContext;
	}
	
	public static void setCurrentContext(AnalyserCache currentContext) {
		AnalyserCache.currentContext = currentContext;
	}
}