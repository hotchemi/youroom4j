package youroom4j.auth;

import java.io.Serializable;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import youroom4j.model.AccessToken;

/**
 * This class provides methods to OAuth Authorization.
 *
 * @author Shintaro Katafuchi
 */
public class OAuthAuthorization implements Serializable {

	private static final long serialVersionUID = 1L;

	private OAuthService service;

	private Token requestToken;

	public OAuthAuthorization() {
	}

	public OAuthAuthorization(String consumerKey, String consumerSecret, String callbackUrl) {
		setOAuthConsumer(consumerKey, consumerSecret, callbackUrl);
	}

	/**
	 * @param consumerKey
	 * @param consumerSecret
	 * @param callbackUrl
	 */
	public final void setOAuthConsumer(String consumerKey, String consumerSecret, String callbackUrl) {
		service = new ServiceBuilder()
			.provider(YouRoomApi.class)
			.apiKey(consumerKey)
			.apiSecret(consumerSecret)
			.callback(callbackUrl)
			.build();
		requestToken = service.getRequestToken();
	}

	/**
	 * @return authorization url.
	 */
	public String getAuthorizationUrl() {
		return service.getAuthorizationUrl(requestToken);
	}

	/**
	 * Return AccessToken Instance.
	 * @param verifier
	 * @return AccessToken
	 */
	public AccessToken getAccessToken(String verifier) {
		Token token = service.getAccessToken(requestToken, new Verifier(verifier));
		return new AccessToken(token.getToken(), token.getSecret());
	}
}