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

  public AccessToken() {
  }

	public AccessToken(String accessToken, String accessTokenSecret) {
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret;
	}

  public void setAcceessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

  public void setAccessTokenSecret(String accessTokenSecret) {
    this.accessTokenSecret = accessTokenSecret;
  }

  public String getAcceessTokenSecret() {
    return accessTokenSecret;
  }

  @Override
  public String toString() {
    return "AccessToken{" +
      "accessToken=" + accessToken +
      ", accessTokenSecret=" + accessTokenSecret + "}";
  }
}