package eu.bibl.banalysis.storage.filter;

import eu.bibl.banalysis.filter.Filter;
import eu.bibl.banalysis.storage.FieldMappingData;

public abstract interface FieldMappingDataFilter extends Filter<FieldMappingData> {

	@Override
	public abstract boolean accept(FieldMappingData t);
}