package eu.bibl.banalysis.storage;

public abstract class Identifiable {

	protected boolean identified;

	public Identifiable identify() {
		identified = true;
		return this;
	}

	public boolean isIdentified() {
		return identified;
	}

	public void setIdentified(boolean identified) {
		this.identified = identified;
	}
}