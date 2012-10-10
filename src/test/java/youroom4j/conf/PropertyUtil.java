package youroom4j.conf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class provides method load properties.
 *
 * @author Shintaro Katafuchi
 */
public final class PropertyUtil {

	private static final String CONF_FILE_PATH = "src/test/java/youroom4j/conf/youroom4j.properties";

	private PropertyUtil () {}

	/**
	 * Get property element.
	 *
	 * @return map contains property.
	 */
	public static Properties loadElements() {
		Properties conf = new Properties();
		try {
			conf.load(new FileInputStream(CONF_FILE_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conf;
	}
}