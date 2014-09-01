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
	
	public static Analyser contextGet(String name) {
		if (currentContext == null)
			return null;
		return currentContext.get(name);
	}
	
	public static void contextCache(Analyser analyser) {
		if (currentContext == null)
			return;
		currentContext.cache(analyser);
	}
	
	public static void contextCache(String name, Analyser analyser) {
		if (currentContext == null)
			return;
		currentContext.cache(name, analyser);
	}
	
	public static AnalyserCache currentContext() {
		return currentContext;
	}
	
	public static void setCurrentContext(AnalyserCache currentContext) {
		AnalyserCache.currentContext = currentContext;
	}
}