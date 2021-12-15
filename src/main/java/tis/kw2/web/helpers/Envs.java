package tis.kw2.web.helpers;

import java.io.IOException;
import java.util.Properties;

public class Envs {

	private static final Properties ENVS = new Properties();
	static {
		try {
			ENVS.load(Envs.class.getClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static String getENV(String key) {
		return ENVS.getProperty(key);
	}

	public static String getENV(String key, String def) {
		return ENVS.getProperty(key, def);
	}
}
