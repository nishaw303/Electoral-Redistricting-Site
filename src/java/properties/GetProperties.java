package properties;

import java.io.IOException;
import java.util.Properties;

public class GetProperties {

	private static GetProperties instance = null;
	private Properties properties;

	protected GetProperties() throws IOException {
		properties = new Properties();
		properties.load(getClass().getResourceAsStream("/resources/config.properties"));
	}

	public static GetProperties getInstance() {
		if (instance == null) {
			try {
				instance = new GetProperties();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return instance;
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}
}
