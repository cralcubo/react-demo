package com.chris.demo.view.utils;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	
	private static String FILE_PATH="src/main/resources/react_fm.properties";
	
	private static final Properties properties = new Properties();

	static {
		try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
			properties.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("There is no file to read secret tokens.");
		}
	}
	
	public static String get(String key) {
		return ofNullable(properties.getProperty(key))
				.orElseThrow(() -> new RuntimeException(format("Property [%s] does not exist.", key)));
	}
}
