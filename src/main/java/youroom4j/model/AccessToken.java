package youroom4j.model;

import java.io.Serializable;

/**
 * Representing one single access token status.
 *
 * @author Shintaro Katafuchi
 */
public class AccessToken implements Serializable {

	private static final long serialVersionUID = 1L;

	private String accessToken;

	private String accessTokenSecret;

	public AccessToken(String accessToken, String AccessTokenSecret) {
		this.accessToken = accessToken;
		this.accessTokenSecret = AccessTokenSecret;
	}

	public String getAceessToken() {
		return accessToken;
	}

	public String getAceessTokenSecret() {
		return accessTokenSecret;
	}

	public AccessToken() {
	}

	@Override
	public String toString() {
		return "AccessToken{" +
						"accessToken=" + accessToken +
						", accessTokenSecret=" + accessTokenSecret + "}";
	}
}