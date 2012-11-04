package youroom4j.impl;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

import youroom4j.YouRoom;
import youroom4j.auth.YouRoomApi;
import youroom4j.exception.YouRoomException;
import youroom4j.model.*;
import youroom4j.util.XmlParse;

import java.io.Serializable;
import java.util.List;
import java.nio.charset.Charset;

/**
 * A java representation of the <a href="http://apidoc.youroom.in">youRoom API</a><br>.
 * To get an instance of YouRoomImpl you should use YouRoomFactory.
 *
 * @author Shintaro Katafuchi
 */
public final class YouRoomImpl implements YouRoom, Serializable {

  private OAuthService service;

  private Token token;

  YouRoomImpl() {
  }

  @Override
	public void setOAuthConsumer(String consumerKey, String consumerSecret, String callback) {
		service = new ServiceBuilder()
			.provider(YouRoomApi.class)
			.apiKey(consumerKey)
			.apiSecret(consumerSecret)
			.callback(callback)
			.build();
		token = service.getRequestToken();
	}

  @Override
  public void setOAuthConsumer(String consumerKey, String consumerSecret) {
    service = new ServiceBuilder()
      .provider(YouRoomApi.class)
      .apiKey(consumerKey)
      .apiSecret(consumerSecret)
      .build();
  }

  @Override
	public String getAuthorizationUrl() throws YouRoomException {
    if (service == null) {
      throw new YouRoomException("Illegal access. You must set oauth consumer before call this method.");
    }
		return service.getAuthorizationUrl(token);
	}

  @Override
	public AccessToken getOAuthAccessToken (String verifier) throws YouRoomException {
    if (service == null) {
      throw new YouRoomException("Illegal access. You must set oauth consumer before call this method.");
    }
    Token requestToken = token;
    token = service.getAccessToken(requestToken, new Verifier(verifier));
    return new AccessToken(token.getToken(), token.getSecret());
  }

  @Override
  public void setOAuthAccessToken(String accessToken, String accessTokenSecret) {
    token = new Token(accessToken, accessTokenSecret);
  }

  @Override
  public List<Entry> getHomeTimeline(Paging paging) throws IllegalArgumentException {
    String url = addParamater(paging, HOME_TIMELINE_URL);
    return XmlParse.getTimeline(getResponse(Verb.GET, url));
  }

  @Override
  public List<Entry> getRoomTimeline(Paging paging) throws IllegalArgumentException {
    StringBuilder tmpUrl = new StringBuilder(ROOM_URL + paging.getGroupParam() + "/entries.xml?");
    String searchQuery = paging.getSearchQuery();
    if (searchQuery != null && searchQuery.length() != 0) {
      tmpUrl.append("search_query=").append(searchQuery).append("&");
    }
    String url = addParamater(paging, tmpUrl.toString());
    return XmlParse.getTimeline(getResponse(Verb.GET, url));
  }

  @Override
  public Entry showEntry(int id, int groupParam) throws IllegalArgumentException {
    String url = ROOM_URL + groupParam + "/entries/" + id + ".xml";
    return XmlParse.getEntry(getResponse(Verb.GET, url));
  }

  @Override
  public Entry createEntry(String content, int parentId, int groupParam) throws IllegalArgumentException {
    String url = ROOM_URL + groupParam + "/entries.xml";
    StringBuilder payload = new StringBuilder("<entry><content>" + content + "</content>");
    if (parentId > 0) {
      payload.append("<parent_id>").append(parentId).append("</parent_id>");
    }
    payload.append("</entry>");
    return XmlParse.getEntry(getPostResponse(Verb.POST, url, payload.toString(), content.length()));
  }

  @Override
  public Entry createEntry(String content, int groupParam) throws IllegalArgumentException {
    String url = ROOM_URL + groupParam + "/entries.xml";
    String payload = "<entry><content>" + content + "</content></entry>";
    return XmlParse.getEntry(getPostResponse(Verb.POST, url, payload, content.length()));
  }

  @Override
  public Entry updateEntry(int id, String content, int groupParam) throws IllegalArgumentException {
    String url = ROOM_URL + groupParam + "/entries/" + id + ".xml";
    String payload = "<entry><content>" + content + "</content></entry>";
    return XmlParse.getEntry(getPostResponse(Verb.PUT, url, payload, content.length()));
  }

  @Override
  public Entry destroyEntry(int id, int groupParam) {
    String url = ROOM_URL + groupParam + "/entries/" + id + ".xml";
    return XmlParse.getEntry(getResponse(Verb.DELETE, url));
  }

  @Override
  public byte[] showAttachment(int id, int groupParam) throws IllegalArgumentException {
    String url = ROOM_URL + groupParam + "/entries/" + id + "/attachment";
    return getBinary(url);
  }

  @Override
  public List<MyGroup> getMyGroups() {
    return XmlParse.getMyGroups(getResponse(Verb.GET, MY_GROUPS_URL));
  }

  @Override
  public User verifyCredentials() {
    return XmlParse.getUser(getResponse(Verb.GET, USER_VERIFY_CREDENTIALS_URL));
  }

  @Override
  public byte[] showPicture(int groupParam, int participationId) throws IllegalArgumentException {
    String url = ROOM_URL + groupParam + "/participations/" + participationId + "/picture";
    return getBinary(url);
  }

  private String addParamater(Paging paging, String url) {
    StringBuilder newUrl = new StringBuilder(url);
    int page = paging.getPage();
    if (page > 0) {
      newUrl.append("page=").append(page).append("&");
    }
    boolean flat = paging.getFlat();
    if (flat) {
      newUrl.append("flat=").append(flat).append("&");
    }
    String readState = paging.getReadState();
    if ("unread".equals(readState)) {
      newUrl.append("read_state=").append(readState).append("&");
    }
    String since = paging.getSince();
    if (since != null) {
      newUrl.append("since=").append(since).append("&");
    }
    return newUrl.toString();
  }

  private byte[] getBinary(String url) throws IllegalArgumentException {
    OAuthRequest request = new OAuthRequest(Verb.GET, url);
    service.signRequest(token, request);
    Response response = request.send();
    if (response.getCode() == 404) {
      throw new IllegalArgumentException("Some of the arguments are invalid.");
    }
    return response.getBody().getBytes(Charset.forName("UTF-8"));
  }

  private String getResponse(Verb verb, String url) throws IllegalArgumentException {
    OAuthRequest request = new OAuthRequest(verb, url);
    service.signRequest(token, request);
    return request.send().getBody();
  }

  private String getPostResponse(Verb verb, String url, String payload, int length) throws IllegalArgumentException {
    if (length > 280) {
      throw new IllegalArgumentException("Number of characters is greater than 280.");
    }
    else if (length == 0) {
      throw new IllegalArgumentException("Number of characters is 0.");
    }
    OAuthRequest request = new OAuthRequest(verb, url);
    request.addPayload(payload);
    request.addHeader("Content-Type", "text/xml;charset=UTF-8");
    service.signRequest(token, request);
    return request.send().getBody();
  }
}