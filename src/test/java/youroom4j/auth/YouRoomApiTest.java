package youroom4j.auth;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.scribe.model.Token;

/**
 * Test for {@link YouRoomApi}.
 * 
 * @author Shintaro Katafuchi
 */
public class YouRoomApiTest {

  private YouRoomApi targetClass = new YouRoomApi();

  @Test
  public void getAccessTokenEndpoint() {
    assertEquals(targetClass.getAccessTokenEndpoint(), "https://www.youroom.in/oauth/access_token");
  }

  @Test
  public void getRequestTokenEndpoint() {
    assertEquals(targetClass.getRequestTokenEndpoint(), "https://www.youroom.in/oauth/request_token");
  }

  @Test
  public void getAuthorizationUrl() {
    assertEquals(targetClass.getAuthorizationUrl(new Token("", "")), "https://www.youroom.in/oauth/authorize/?oauth_token=");
  }

}