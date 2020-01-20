package com.anderscore.refactoring.csv;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CsvExporterImplTest {
	
	private CsvExporterImpl exporter;
	
	@BeforeEach
	public void setUp() {
		exporter = new CsvExporterImpl();
	}
	
	@Test
	public void testGetHeader_ResourceBundle() {
		String header = exporter.getHeader(new ExportableField(null, "field1"));
		
		assertEquals("My Field", header);
	}
	
	
	@Test
	public void testGetHeader_Label() {
		String header = exporter.getHeader(new ExportableField("Field 2", "field2"));
		
		assertEquals("Field 2", header);
	}
	
	@Test
	public void testGetHeader_Property() {
		String header = exporter.getHeader(new ExportableField(null, "field3"));
		
		assertEquals("field3", header);
	}
	
	@Test
	public void testGetHeaders() {
		ExportableField field1 = new ExportableField(null, "field1");
		ExportableField field2 = new ExportableField("Field 2" , "field2");
		ExportableField field3 = new ExportableField(null, "field3");
				
		String header = exporter.getHeaders(asList(field1, field2, field3));
		
		assertEquals("My Field;Field 2;field3", header);
	}
}