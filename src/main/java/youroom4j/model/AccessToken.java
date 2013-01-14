package youroom4j.model;

import java.io.Serializable;

/**
 * Representing one single access token status.
 *
 * @author Shintaro Katafuchi
 */
public class AccessToken implements Serializable {

  private static final long serialVersionUID = 1L;

  private String token;

  private String tokenSecret;

  public AccessToken() {
  }

  public AccessToken(String token, String tokenSecret) {
    this.token = token;
    this.tokenSecret = tokenSecret;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getTokenSecret() {
    return tokenSecret;
  }

  public void setTokenSecret(String tokenSecret) {
    this.tokenSecret = tokenSecret;
  }

  @Override
  public String toString() {
    return "AccessToken{" +
        "token=" + token +
        ", tokenSecret=" + tokenSecret + "}";
  }
}