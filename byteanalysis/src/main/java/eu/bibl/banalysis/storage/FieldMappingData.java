package eu.bibl.banalysis.storage;

public class FieldMappingData extends Identifiable {

	protected ClassMappingData fieldOwner;
	protected ClassMappingData methodOwner;
	protected MappingData name;
	protected MappingData desc;
	protected boolean isStatic;

	public FieldMappingData(MappingData name, MappingData desc, boolean isStatic) {
		this(null, null, name, desc, isStatic);
	}

	public FieldMappingData(ClassMappingData fieldOwner, ClassMappingData methodOwner, MappingData name, MappingData desc, boolean isStatic) {
		this.fieldOwner = fieldOwner;
		this.methodOwner = methodOwner;
		this.name = name;
		this.desc = desc;
		this.isStatic = isStatic;
	}

	public MappingData getFieldOwner() {
		return fieldOwner;
	}

	public FieldMappingData setFieldOwner(ClassMappingData fieldOwner) {
		this.fieldOwner = fieldOwner;
		return this;
	}

	public MappingData getMethodOwner() {
		return methodOwner;
	}

	public FieldMappingData setMethodOwner(ClassMappingData methodOwner) {
		this.methodOwner = methodOwner;
		return this;
	}

	public MappingData getName() {
		return name;
	}

	public FieldMappingData setName(MappingData name) {
		this.name = name;
		return this;
	}

	public MappingData getDesc() {
		return desc;
	}

	public FieldMappingData setDesc(MappingData desc) {
		this.desc = desc;
		return this;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public FieldMappingData setStatic(boolean isStatic) {
		this.isStatic = isStatic;
		return this;
	}

	@Override
	public FieldMappingData identify() {
		super.identify();
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((desc == null) ? 0 : desc.hashCode());
		result = (prime * result) + ((fieldOwner == null) ? 0 : fieldOwner.hashCode());
		result = (prime * result) + (isStatic ? 1231 : 1237);
		result = (prime * result) + ((methodOwner == null) ? 0 : methodOwner.hashCode());
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
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
		FieldMappingData other = (FieldMappingData) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (fieldOwner == null) {
			if (other.fieldOwner != null)
				return false;
		} else if (!fieldOwner.equals(other.fieldOwner))
			return false;
		if (isStatic != other.isStatic)
			return false;
		if (methodOwner == null) {
			if (other.methodOwner != null)
				return false;
		} else if (!methodOwner.equals(other.methodOwner))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}