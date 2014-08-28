package eu.bibl.banalysis.storage;

import java.util.ArrayList;
import java.util.List;

import eu.bibl.banalysis.storage.filter.CallbackMappingDataFilter;
import eu.bibl.banalysis.storage.filter.FieldMappingDataFilter;

public class HookMap {

	protected List<ClassMappingData> classes;
	protected List<FieldMappingData> fields;
	protected List<CallbackMappingData> methods;

	public HookMap() {
		classes = new ArrayList<ClassMappingData>();
		fields = new ArrayList<FieldMappingData>();
		methods = new ArrayList<CallbackMappingData>();
	}

	public void addClass(ClassMappingData MappingData) {
		classes.add(MappingData);
	}

	public void addField(FieldMappingData field) {
		fields.add(field);
	}

	public void addMethod(CallbackMappingData method) {
		methods.add(method);
	}

	public List<ClassMappingData> getClasses() {
		return classes;
	}

	public MappingData getClassByOfuscatedName(String obfName) {
		for (MappingData mappingData : classes) {
			if (mappingData.obfuscatedName.equals(obfName))
				return mappingData;
		}
		return null;
	}

	public MappingData getClassByRefactoredName(String refacName) {
		for (MappingData mappedData : classes) {
			if (mappedData.refactoredName.equals(refacName))
				return mappedData;
		}
		return null;
	}

	public List<FieldMappingData> getFields() {
		return fields;
	}

	public List<FieldMappingData> getFieldsByObfuscatedFieldOwner(String obfName) {
		List<FieldMappingData> fields = new ArrayList<FieldMappingData>();
		for (FieldMappingData field : this.fields) {
			if (field.fieldOwner.obfuscatedName.equals(obfName))
				fields.add(field);
		}
		return fields;
	}

	public List<FieldMappingData> getFieldsByRefactoredFieldOwner(String refacName) {
		List<FieldMappingData> fields = new ArrayList<FieldMappingData>();
		for (FieldMappingData field : this.fields) {
			if (field.fieldOwner.refactoredName.equals(refacName))
				fields.add(field);
		}
		return fields;
	}

	public List<FieldMappingData> getFieldsByObfuscatedMethodOwner(String obfName) {
		List<FieldMappingData> fields = new ArrayList<FieldMappingData>();
		for (FieldMappingData field : this.fields) {
			if (field.methodOwner.obfuscatedName.equals(obfName))
				fields.add(field);
		}
		return fields;
	}

	public List<FieldMappingData> getFieldsByRefactoredMethodOwner(String refacName) {
		List<FieldMappingData> fields = new ArrayList<FieldMappingData>();
		for (FieldMappingData field : this.fields) {
			if (field.methodOwner.refactoredName.equals(refacName))
				fields.add(field);
		}
		return fields;
	}

	public List<CallbackMappingData> getMethods() {
		return methods;
	}

	public List<CallbackMappingData> getMethodsByObfuscatedFieldOwner(String obfName) {
		List<CallbackMappingData> methods = new ArrayList<CallbackMappingData>();
		for (CallbackMappingData method : this.methods) {
			if (method.methodOwner.obfuscatedName.equals(obfName))
				methods.add(method);
		}
		return methods;
	}

	public List<CallbackMappingData> getMethodsByRefactoredFieldOwner(String refacName) {
		List<CallbackMappingData> methods = new ArrayList<CallbackMappingData>();
		for (CallbackMappingData method : this.methods) {
			if (method.methodOwner.refactoredName.equals(refacName))
				methods.add(method);
		}
		return methods;
	}

	public List<CallbackMappingData> getMethodsByObfuscatedMethodOwner(String obfName) {
		List<CallbackMappingData> methods = new ArrayList<CallbackMappingData>();
		for (CallbackMappingData method : this.methods) {
			if (method.callbackOwner.obfuscatedName.equals(obfName))
				methods.add(method);
		}
		return methods;
	}

	public List<CallbackMappingData> getMethodsByRefactoredMethodOwner(String refacName) {
		List<CallbackMappingData> methods = new ArrayList<CallbackMappingData>();
		for (CallbackMappingData method : this.methods) {
			if (method.callbackOwner.refactoredName.equals(refacName))
				methods.add(method);
		}
		return methods;
	}

	public List<FieldMappingData> filterFields(FieldMappingDataFilter filter) {
		List<FieldMappingData> filteredFields = new ArrayList<FieldMappingData>();
		for (FieldMappingData data : fields) {
			if (filter.accept(data))
				filteredFields.add(data);
		}
		return filteredFields;
	}

	public List<CallbackMappingData> filterMethods(CallbackMappingDataFilter filter) {
		List<CallbackMappingData> filteredMethods = new ArrayList<CallbackMappingData>();
		for (CallbackMappingData data : methods) {
			if (filter.accept(data))
				filteredMethods.add(data);
		}
		return filteredMethods;
	}
}