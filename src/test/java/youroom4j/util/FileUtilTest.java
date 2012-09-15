package youroom4j.util;

import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Test for {@link FileUtil}.
 * 
 * @author Shintaro Katafuchi
 */
public class FileUtilTest {

	@Test
	public void testLoadConf() {
		Map<String, String> targetMap = FileUtil.loadConf("./youroom4j.conf");
		assertEquals(targetMap.get("consumerKey"), "zAEMG8v9izem77IuIRTN");
		assertEquals(targetMap.get("consumerSecret"), "6z9r7cxcfJfxmmQNzVUYs6q4hXuG9hXPr6yHQMJz");
		assertEquals(targetMap.get("callBack"), "http://twitter.com");
		assertEquals(targetMap.get("accessToken"), "CPUO4nEXV91xJ8s6yNVO");
		assertEquals(targetMap.get("accessTokenSecret"), "IAz8Wm1Sy1cht3zXsdzr0VpkYds69egi7UZUpNIR");
	}
	
}