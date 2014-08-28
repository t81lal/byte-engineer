package eu.bibl.banalysis.storage.filter;

import eu.bibl.banalysis.filter.Filter;
import eu.bibl.banalysis.storage.CallbackMappingData;

public abstract interface CallbackMappingDataFilter extends Filter<CallbackMappingData> {

	@Override
	public abstract boolean accept(CallbackMappingData t);
}