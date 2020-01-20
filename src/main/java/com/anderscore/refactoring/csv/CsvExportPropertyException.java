package com.anderscore.refactoring.csv;

public class CsvExportPropertyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CsvExportPropertyException(String message) {
        super(message);
    }

    public CsvExportPropertyException(Throwable t) {
        super(t);
    }


    public CsvExportPropertyException(final String message, final Throwable cause) {
        super(message, cause);
    }
}