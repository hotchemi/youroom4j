package youroom4j.auth;

import java.util.Map;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import youroom4j.util.FileUtil;

/**
 * Authenticate OAuth.
 *
 * @author Shintaro Katafuchi
 */
public class Authorization {

	/**
	 * Return OAuthService instance.
	 *
	 * @return service
	 */
	public static OAuthService getOauthService() {
		Map<String, String> map = FileUtil.loadConf("./youroom4j.properties");
		return new ServiceBuilder()
						.provider(YouRoomApi.class)
						.apiKey(map.get("consumerKey"))
						.apiSecret(map.get("consumerSecret"))
						.callback(map.get("callBack"))
						.build();
	}

	/**
	 * Return accessToken instance.
	 *
	 * @return Token
	 */
	public static Token getToken() {
		Map<String, String> map = FileUtil.loadConf("./youroom4j.properties");
		return new Token(map.get("accessToken"), map.get("accessTokenSecret"));
	}

}