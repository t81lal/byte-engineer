package eu.bibl.banalysis.asm.inject;

import eu.bibl.banalysis.asm.ClassNode;
import eu.bibl.banalysis.storage.HookMap;

public abstract class Injector {

	protected ClassNode cn;
	protected HookMap hookMap;

	public Injector(HookMap hookMap) {
		this.hookMap = hookMap;
	}

	public abstract boolean shouldInject(ClassNode cn) throws Exception;

	public abstract void inject() throws Exception;

	public final void runInjector(ClassNode cn) throws InjectionException {
		try {
			if (shouldInject(cn)) {
				this.cn = cn;
				inject();
			}
		} catch (Exception e) {
			throw new InjectionException(e);
		}
	}
}