package eu.bibl.banalysis.analyse;

import org.objectweb.asm.Opcodes;

import eu.bibl.banalysis.asm.ClassNode;
import eu.bibl.banalysis.storage.CallbackMappingData;
import eu.bibl.banalysis.storage.ClassMappingData;
import eu.bibl.banalysis.storage.FieldMappingData;
import eu.bibl.banalysis.storage.HookMap;
import eu.bibl.banalysis.storage.InterfaceMappingData;
import eu.bibl.banalysis.storage.classes.ClassContainer;

/**
 * @author sc4re
 */
public abstract class Analyser implements Opcodes {
	
	/** Name of Analyser **/
	protected final String name;
	
	protected ClassContainer container;
	/** HookMap to find/add mappings to **/
	protected HookMap hookMap;
	
	/** Array of field mappings needed to be identified. **/
	protected FieldMappingData[] fieldHooks;
	/** Array of method mappings needed to be identified **/
	protected CallbackMappingData[] methodHooks;
	
	protected ClassMappingData classHook;
	
	public Analyser(ClassContainer container, HookMap hookMap) {
		name = getClass().getSimpleName().replace("Analyser", "");
		this.container = container;
		this.hookMap = hookMap;
		fieldHooks = new FieldMappingData[0];
		methodHooks = new CallbackMappingData[0];
		classHook = new ClassMappingData(name);
		
		if (AnalyserCache.currentContext() != null)
			AnalyserCache.currentContext().cache(this);
	}
	
	public Analyser(String name, ClassContainer container, HookMap hookMap) {
		this.name = name;
		this.container = container;
		this.hookMap = hookMap;
		fieldHooks = new FieldMappingData[0];
		methodHooks = new CallbackMappingData[0];
		classHook = new ClassMappingData(name);
		
		if (AnalyserCache.currentContext() != null)
			AnalyserCache.currentContext().cache(this);
	}
	
	public void addField(FieldMappingData field) {
		if (field.getFieldOwner() == null)
			field.setFieldOwner(classHook);
		if (field.getMethodOwner() == null)
			field.setMethodOwner(classHook);
		hookMap.addField(field);
	}
	
	public void addMethod(CallbackMappingData method) {
		if (method.getMethodOwner() == null)
			method.setMethodOwner(classHook);
		if (method.getCallbackOwner() == null)
			method.setCallbackOwner(classHook);
		
		hookMap.addMethod(method);
	}
	
	/** ClassNode being analysed **/
	protected ClassNode cn;
	
	public void run(ClassNode cn) {
		this.cn = cn;
		if (accept()) {
			classHook.setIdentified(true);
			classHook.setObfuscatedName(cn.name);
			InterfaceMappingData imd = run();
			if (imd != null)
				classHook.setInterfaceData(imd);
			hookMap.addClass(classHook);
		}
	}
	
	protected final HookMap getHookMap() {
		return hookMap;
	}
	
	protected final ClassNode getNode(String name) {
		return container.getNodes().get(name);
	}
	
	public ClassContainer getContainer() {
		return container;
	}
	
	public void setContainer(ClassContainer container) {
		this.container = container;
	}
	
	public void setHookMap(HookMap hookMap) {
		this.hookMap = hookMap;
	}
	
	/**
	 * Checks if this ClassNode should be analysed by this analyser.
	 * @return True if the ClassNode is to be run on, false otherwise.
	 */
	public abstract boolean accept();
	
	/**
	 * Runs the analyser on the given ClassNode.
	 * @return Interface to implement the class hook or null if none is needed
	 */
	public abstract InterfaceMappingData run();
	
	/**
	 * Returns whether the analyser is successfully identified.
	 * @return True if the analyser is successfully identified, false otherwise.
	 */
	public boolean isAnalysed() {
		if (!classHook.isIdentified())
			return false;
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
	
	public String getName() {
		return name;
	}
}