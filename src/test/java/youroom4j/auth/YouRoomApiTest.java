package youroom4j.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.junit.Test;
import org.scribe.model.Token;

/**
 * Test for {@link YouRoomApi}.
 * 
 * @author Shintaro Katafuchi
 */
public class YouRoomApiTest {

	private static final String ACCESS_TOKEN_RESOURCE = "https://www.youroom.in/oauth/access_token";

	private static final String REQUEST_TOKEN_RESOURCE = "https://www.youroom.in/oauth/request_token";

	private static final String AUTHORIZE_URL = "https://www.youroom.in/oauth/authorize/?oauth_token=";

	private YouRoomApi targetClass = new YouRoomApi();

	@Test
	public void getAccessTokenEndpoint() {
		assertSame(targetClass.getAccessTokenEndpoint(), ACCESS_TOKEN_RESOURCE);
	}

	@Test
	public void getRequestTokenEndpoint() {
		assertSame(targetClass.getRequestTokenEndpoint(), REQUEST_TOKEN_RESOURCE);
	}

	@Test
	public void getAuthorizationUrl() {
		assertEquals(targetClass.getAuthorizationUrl(new Token("", "")), AUTHORIZE_URL);
	}
}