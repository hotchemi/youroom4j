package youroom4j;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;
import youroom4j.auth.YouRoomApi;

/**
 * This class provides methods to create ready to use YouRoomClient
 * instance by different ways.
 *
 * @author Shintaro Katafuchi
 * @see YouRoomClient
 */
public class YouRoomBuilder {

    private final OAuthService service;

    private Token accessToken;

    /**
     * Creates instance of YouRoomBuilder ready to authenticate user
     *
     * @param consumerKey
     * @param consumerSecret
     */
    private YouRoomBuilder(String ConsumerKey, String consumerSecret) {
			service = new ServiceBuilder()
							.provider(YouRoomApi.class)
							.apiKey(ConsumerKey)
							.apiSecret(consumerSecret)
							.build();
    }

    /**
     * Build ready to use YouRoomClient with specified consumerKey and consumerSecret
     * with specified access token (without authenticator).
     *
     * @param consumerKey
     * @param consumerSecret
     * @param accessToken
     * @return ready to use YouRoom
     * @see YouRoom
     */
    public static YouRoom build(String consumerKey, String consumerSecret, Token accessToken) {
        YouRoomBuilder builder = new YouRoomBuilder(consumerKey, consumerSecret);
        builder.accessToken = accessToken;
        return builder.builderYouRoom();
    }

    /**
     * Build ready to use YouRoomClient with specified appKey and appSecret
     * with specified access token (without authenticator).
     *
     * @param consumerKey
     * @param consumerSecret
     * @param token
     * @param secret
     * @return ready to use youroom client
     * @see YouRoomClient
     */
    public static YouRoom build(String consumerKey, String consumerSecret, String token, String secret) {
        YouRoomBuilder builder = new YouRoomBuilder(consumerKey, consumerSecret);
        builder.accessToken = new Token(token, secret);
        return builder.builderYouRoom();
    }

    /**
     * @return new instance of YouRoom with current OAuth client and access token
     */
    private YouRoom builderYouRoom() {
        return new YouRoomClient(service, accessToken);
    }

}