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

	PropertyUtil () {
	}

	public static Properties load() {
		Properties conf = new Properties();
		try {
			conf.load(new FileInputStream("src/test/java/youroom4j/conf/youroom4j.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conf;
	}
}