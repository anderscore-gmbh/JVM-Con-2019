package com.anderscore.refactoring.csv;

import java.util.List;

/**
 * Enables CSV export for selected fields of arbitrary objects.
 */
public interface CsvExporter {
	
	/**
	 * Exports an arbitrary object as CSV (without headers).
	 * 
	 * @param fields Object fields to include.
	 * @param object Object to export.
	 * @param ignoreMissingFields Continue export if one or more specified properties can not be found on the object.
	 
	 * @return CSV representation of the supplied object.
	 */
	String export(List<ExportableField> fields, Object object, boolean ignoreMissingFields);
	
	/**
	 * Exports an arbitrary object as CSV (with headers).
	 * 
	 * @param fields Object fields to include.
	 * @param object Object to export.
	 * @param ignoreMissingFields Continue export if one or more specified properties can not be found on the object.
	 
	 * @return CSV representation of the supplied object.
	 */
	String exportWithHeaders(List<ExportableField> fields, Object object, boolean ignoreMissingProperties);
}