package io.github.educontessi.domain.helpers.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

	public static String getProperty(String property) {
		String value = null;
		Properties props = new Properties();
		try {
			FileInputStream file = new FileInputStream("./src/main/resources/application.properties");
			props.load(file);
			value = props.getProperty(property);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

}
