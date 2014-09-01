package eu.bibl.banalysis.storage;

import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class CallbackMappingData extends Identifiable {
	
	protected ClassMappingData methodOwner;
	protected ClassMappingData callbackOwner;
	protected MappingData methodName;
	protected MappingData methodDesc;
	protected MappingData callbackName;
	protected MappingData callbackDesc;
	protected boolean isStatic;
	
	public CallbackMappingData(MappingData methodName, MappingData methodDesc, MappingData callbackName, MappingData callbackDesc, boolean isStatic) {
		this(null, null, methodName, methodDesc, callbackName, callbackDesc, isStatic);
	}
	
	public CallbackMappingData(ClassMappingData methodOwner, ClassMappingData callbackOwner, MappingData methodName, MappingData methodDesc, MappingData callbackName, MappingData callbackDesc, boolean isStatic) {
		this.methodOwner = methodOwner;
		this.callbackOwner = callbackOwner;
		this.methodName = methodName;
		this.methodDesc = methodDesc;
		this.callbackName = callbackName;
		this.callbackDesc = callbackDesc;
		this.isStatic = isStatic;
	}
	
	public CallbackMappingData buildObfMethod(MethodInsnNode min) {
		if (methodName != null) {
			methodName.setObfuscatedName(min.name);
		}
		if (methodDesc != null) {
			methodDesc.setObfuscatedName(min.desc);
		}
		return this;
	}
	
	public CallbackMappingData buildObfMethod(MethodNode mn) {
		if (methodName != null) {
			methodName.setObfuscatedName(mn.name);
		}
		if (methodDesc != null) {
			methodDesc.setObfuscatedName(mn.desc);
		}
		return this;
	}
	
	public CallbackMappingData buildRefacMethod(MethodInsnNode min) {
		if (methodName != null) {
			methodName.setRefactoredName(min.name);
		}
		if (methodDesc != null) {
			methodDesc.setRefactoredName(min.desc);
		}
		return this;
	}
	
	public CallbackMappingData buildRefacMethod(MethodNode mn) {
		if (methodName != null) {
			methodName.setRefactoredName(mn.name);
		}
		if (methodDesc != null) {
			methodDesc.setRefactoredName(mn.desc);
		}
		return this;
	}
	
	public CallbackMappingData buildObfCallback(MethodInsnNode min) {
		if (callbackName != null) {
			callbackName.setObfuscatedName(min.name);
		}
		if (methodDesc != null) {
			methodDesc.setObfuscatedName(min.desc);
		}
		return this;
	}
	
	public CallbackMappingData buildObfCallback(MethodNode mn) {
		if (callbackName != null) {
			callbackName.setObfuscatedName(mn.name);
		}
		if (callbackDesc != null) {
			callbackDesc.setObfuscatedName(mn.desc);
		}
		return this;
	}
	
	public CallbackMappingData buildRefacCallback(MethodInsnNode min) {
		if (callbackName != null) {
			callbackName.setRefactoredName(min.name);
		}
		if (callbackDesc != null) {
			callbackDesc.setRefactoredName(min.desc);
		}
		return this;
	}
	
	public CallbackMappingData buildRefacCallback(MethodNode mn) {
		if (callbackName != null) {
			callbackName.setRefactoredName(mn.name);
		}
		if (callbackDesc != null) {
			callbackDesc.setRefactoredName(mn.desc);
		}
		return this;
	}
	
	public MappingData getMethodOwner() {
		return methodOwner;
	}
	
	public CallbackMappingData setMethodOwner(ClassMappingData methodOwner) {
		this.methodOwner = methodOwner;
		return this;
	}
	
	public MappingData getCallbackOwner() {
		return callbackOwner;
	}
	
	public CallbackMappingData setCallbackOwner(ClassMappingData callbackOwner) {
		this.callbackOwner = callbackOwner;
		return this;
	}
	
	public MappingData getMethodName() {
		return methodName;
	}
	
	public CallbackMappingData setMethodName(MappingData methodName) {
		this.methodName = methodName;
		return this;
	}
	
	public MappingData getMethodDesc() {
		return methodDesc;
	}
	
	public CallbackMappingData setMethodDesc(MappingData methodDesc) {
		this.methodDesc = methodDesc;
		return this;
	}
	
	public MappingData getCallbackName() {
		return callbackName;
	}
	
	public CallbackMappingData setCallbackName(MappingData callbackName) {
		this.callbackName = callbackName;
		return this;
	}
	
	public MappingData getCallbackDesc() {
		return callbackDesc;
	}
	
	public CallbackMappingData setCallbackDesc(MappingData callbackDesc) {
		this.callbackDesc = callbackDesc;
		return this;
	}
	
	public boolean isStatic() {
		return isStatic;
	}
	
	public CallbackMappingData setStatic(boolean isStatic) {
		this.isStatic = isStatic;
		return this;
	}
	
	@Override
	public CallbackMappingData identify() {
		super.identify();
		return this;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((callbackDesc == null) ? 0 : callbackDesc.hashCode());
		result = (prime * result) + ((callbackName == null) ? 0 : callbackName.hashCode());
		result = (prime * result) + ((callbackOwner == null) ? 0 : callbackOwner.hashCode());
		result = (prime * result) + (isStatic ? 1231 : 1237);
		result = (prime * result) + ((methodDesc == null) ? 0 : methodDesc.hashCode());
		result = (prime * result) + ((methodName == null) ? 0 : methodName.hashCode());
		result = (prime * result) + ((methodOwner == null) ? 0 : methodOwner.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CallbackMappingData other = (CallbackMappingData) obj;
		if (callbackDesc == null) {
			if (other.callbackDesc != null)
				return false;
		} else if (!callbackDesc.equals(other.callbackDesc))
			return false;
		if (callbackName == null) {
			if (other.callbackName != null)
				return false;
		} else if (!callbackName.equals(other.callbackName))
			return false;
		if (callbackOwner == null) {
			if (other.callbackOwner != null)
				return false;
		} else if (!callbackOwner.equals(other.callbackOwner))
			return false;
		if (isStatic != other.isStatic)
			return false;
		if (methodDesc == null) {
			if (other.methodDesc != null)
				return false;
		} else if (!methodDesc.equals(other.methodDesc))
			return false;
		if (methodName == null) {
			if (other.methodName != null)
				return false;
		} else if (!methodName.equals(other.methodName))
			return false;
		if (methodOwner == null) {
			if (other.methodOwner != null)
				return false;
		} else if (!methodOwner.equals(other.methodOwner))
			return false;
		return true;
	}
}