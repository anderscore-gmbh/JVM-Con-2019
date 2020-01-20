package com.anderscore.refactoring.csv;

/**
 * Object field mapped to a column for CSV export.
 */
public class ExportableField{

    private String label;
    private String property;

    public ExportableField(String label, String property) {
        this.label = label;
        this.property = property;
    }
    
    public String getLabel() {
        return label;
    }

    public String getProperty() {
        return property;
    }
}