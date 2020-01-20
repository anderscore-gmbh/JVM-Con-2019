package com.anderscore.refactoring.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static java.util.Locale.GERMAN;
import static java.util.ResourceBundle.getBundle;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.beanutils.PropertyUtils.getProperty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class CsvExporterImpl implements CsvExporter {

	private static final Logger logger = LoggerFactory.getLogger(CsvExporterImpl.class);

	private static final String RESOURCE_BUNDLE_ID = "messages";

	public String export(List<ExportableField> fields, Object object, boolean ignoreMissingProperties) {
		return fields.stream()
				.map(f -> getPropertyValueAsString(f, object, ignoreMissingProperties))
				.collect(joining(";"));
	}

	public String exportWithHeaders(List<ExportableField> fields, Object object, boolean ignoreMissingProperties) {
		return getHeaders(fields) + "\n" + export(fields, object, ignoreMissingProperties);
	}

	protected String getHeaders(List<ExportableField> fields) {
		return fields.stream()
				.map(this::getHeader)
				.collect(joining(";"));
	}

	protected String getHeader(ExportableField field) {
		String property = field.getProperty();

		String header = getResourceValueFromBundle(property);
		if (isNotBlank(header)) {
			return header;
		}

		header = field.getLabel();
		if (isNotBlank(header)) {
			return header;
		}

		return field.getProperty();
	}

	protected String getResourceValueFromBundle(String key) {
		try {
			ResourceBundle resourceBundle = getBundle(RESOURCE_BUNDLE_ID, GERMAN);

			if (resourceBundle.containsKey(key)) {
				return resourceBundle.getString(key);
			}

		} catch (MissingResourceException e) {
			logger.info("Resource Bundle {} not found.", RESOURCE_BUNDLE_ID);
		}

		return null;
	}

	protected String getPropertyValueAsString(ExportableField field, Object object, boolean ignoreMissingProperty) {
		try {
			Object propertyValue = getProperty(object, field.getProperty());
			String propertyValueStr = propertyValue != null ? propertyValue.toString() : "";

			return removeIllegalCharacters(propertyValueStr);

		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			if (ignoreMissingProperty) {
				logger.info("Ignoring unknown property '{}'.", field.getProperty());
				return "";
			}

			throw new CsvExportPropertyException(e);
		}
	}

	protected String removeIllegalCharacters(String in) {
		return in.replace(";", "");
	}
}