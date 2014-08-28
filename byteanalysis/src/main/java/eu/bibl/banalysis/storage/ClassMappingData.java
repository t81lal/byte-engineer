package eu.bibl.banalysis.storage;

public class ClassMappingData extends MappingData {

	protected InterfaceMappingData interfaceData;

	public ClassMappingData(String refactoredName) {
		super(refactoredName);
	}

	public ClassMappingData(String refactoredName, InterfaceMappingData interfaceData) {
		super(refactoredName);
		this.interfaceData = interfaceData;
	}

	public ClassMappingData(String obfuscatedName, String refactoredName, InterfaceMappingData interfaceData) {
		super(obfuscatedName, refactoredName);
		this.interfaceData = interfaceData;
	}

	public InterfaceMappingData getInterfaceMappingData() {
		return interfaceData;
	}

	public void setInterfaceData(InterfaceMappingData mappingData) {
		interfaceData = mappingData;
	}
}