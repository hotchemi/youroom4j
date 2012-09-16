package youroom4j.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * file util function.
 * 
 * @author Shintaro Katafuchi
 */
public class FileUtil {
	
	/**
	 * Load configuration file and return key and value.
	 * 
	 * @param filePath
	 * @return map key and value.
	 */
	public static Map<String, String> loadConf(String filePath) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("consumerKey", prop.getProperty("consumerKey").toString());
		map.put("consumerSecret", prop.getProperty("consumerSecret").toString());
		map.put("callBack", prop.getProperty("callBack").toString());
		map.put("accessToken", prop.getProperty("accessToken").toString());
		map.put("accessTokenSecret", prop.getProperty("accessTokenSecret").toString());
		return map;
	}
	
}