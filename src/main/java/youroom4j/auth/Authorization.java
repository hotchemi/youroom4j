package youroom4j.auth;

import java.util.Map;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;

import youroom4j.util.FileUtil;

/**
 * authenticate OAuth.
 * @author Shintaro Katafuchi
 */
public class Authorization {

	/**
	 * get OAuthService instance.
	 * @return service
	 */
	public OAuthService getOauthService() {
		Map<String, String> map = FileUtil.loadConf("./youroom4j.conf");

		OAuthService service = new ServiceBuilder()
			.provider(YouRoomApi.class)
			.apiKey(map.get("consumerKey"))
			.apiSecret(map.get("consumerSecret"))
			.callback(map.get("callBack"))
			.build();
		return service;
	}
}