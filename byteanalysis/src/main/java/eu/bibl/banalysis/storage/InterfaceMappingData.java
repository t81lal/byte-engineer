package eu.bibl.banalysis.storage;

public class InterfaceMappingData {

	protected String interfaceName;

	public InterfaceMappingData(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public InterfaceMappingData setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((interfaceName == null) ? 0 : interfaceName.hashCode());
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
		InterfaceMappingData other = (InterfaceMappingData) obj;
		if (interfaceName == null) {
			if (other.interfaceName != null)
				return false;
		} else if (!interfaceName.equals(other.interfaceName))
			return false;
		return true;
	}
}