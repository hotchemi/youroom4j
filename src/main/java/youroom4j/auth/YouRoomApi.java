package youroom4j.auth;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * @author Shintaro Katafuchi
 */
public class YouRoomApi extends DefaultApi10a {
	private static final String ACCESS_TOKEN_RESOURCE = "https://www.youroom.in/oauth/access_token";
	private static final String REQUEST_TOKEN_RESOURCE = "https://www.youroom.in/oauth/request_token";
	private static final String AUTHORIZE_URL = "https://www.youroom.in/oauth/authorize/?oauth_token=%s";
	  
	@Override
	public String getAccessTokenEndpoint() {
		return ACCESS_TOKEN_RESOURCE; 
	}
	
	@Override
	public String getRequestTokenEndpoint() {
		return REQUEST_TOKEN_RESOURCE;
	}

	@Override
	public String getAuthorizationUrl(Token requestToken) {
		return String.format(AUTHORIZE_URL, requestToken.getToken());
	}
}