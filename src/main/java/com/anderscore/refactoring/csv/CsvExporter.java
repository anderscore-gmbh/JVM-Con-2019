package com.anderscore.refactoring.csv;

import java.util.List;

public interface CsvExporter {
	
	String export(List<ExportableField> fields, Object object, boolean ignoreMissingProperties);
	
	String exportWithHeaders(List<ExportableField> fields, Object object, boolean ignoreMissingProperties);
}