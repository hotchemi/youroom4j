package youroom4j.examples.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import youroom4j.auth.Authorization;

/**
 * Sample for Oauth.
 * Get access token and access the api.
 * 
 * @author Shintaro Katafuchi
 */
public class GetAccessToken {
	public static void main(String[] args) throws Exception {
		
		OAuthService service = Authorization.getOauthService();
		Token requestToken = service.getRequestToken();
		String authUrl = service.getAuthorizationUrl(requestToken);
		
		System.out.println(authUrl);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pin = "";

		try {
			pin = br.readLine();
		} catch (IOException e) {

		}

		Token accessToken = service.getAccessToken(requestToken, new Verifier(pin));
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.youroom.in/entries.json");

		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println(response.getBody());
	}
	
}