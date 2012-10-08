package youroom4j.impl;

import java.nio.charset.Charset;
import java.util.List;
import org.scribe.builder.ServiceBuilder;

import youroom4j.model.Entry;
import youroom4j.model.Paging;
import youroom4j.model.MyGroup;
import youroom4j.model.User;
import youroom4j.util.XmlParse;
import youroom4j.auth.YouRoomApi;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import youroom4j.YouRoom;

/**
 * A java representation of the <a href="http://apidoc.youroom.in">youRoom API</a><br>.
 * To get an instance of YouRoomImpl you should use YouRoomFactory.
 *
 * @author Shintaro Katafuchi
 */
public class YouRoomImpl implements YouRoom {

	private OAuthService service;

	private Token token;

	YouRoomImpl() {}

	@Override
	public void setOAuthConsumer(String consumerKey, String consumerSecret) {
		this.service = new ServiceBuilder()
						.provider(YouRoomApi.class)
						.apiKey(consumerKey)
						.apiSecret(consumerSecret)
						.build();
	}

	@Override
	public void setOAuthAccessToken(String accessToken, String accessTokenSecret) {
		this.token = new Token(accessToken, accessTokenSecret);
	}

	@Override
	public List<Entry> getHomeTimeline(Paging paging) throws IllegalArgumentException {
		OAuthRequest request = new OAuthRequest(Verb.GET, addParamater(paging, HOME_TIMELINE_URL));
		service.signRequest(token, request);
		return XmlParse.getTimeline(request.send().getBody());
	}

	@Override
	public List<Entry> getRoomTimeline(Paging paging) throws IllegalArgumentException {
		StringBuilder url = new StringBuilder(ROOM_URL + paging.getGroupParam() + "/entries.xml?");

		String searchQuery = paging.getSearchQuery();
		if (searchQuery != null && searchQuery.length() != 0)
			url.append("search_query=").append(searchQuery).append("&");

		OAuthRequest request = new OAuthRequest(Verb.GET, addParamater(paging, url.toString()));
		service.signRequest(token, request);
		return XmlParse.getTimeline(request.send().getBody());
	}

	@Override
	public Entry showEntry(int id, int groupParam) throws IllegalArgumentException {
		String url = ROOM_URL + groupParam + "/entries/" + id + ".xml";
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		service.signRequest(token, request);
		return XmlParse.getEntry(request.send().getBody());
	}

	@Override
	public Entry createEntry(String content, int parentId, int groupParam) throws IllegalArgumentException {
		assertLength(content.length());
		String url = ROOM_URL + groupParam + "/entries.xml";
		OAuthRequest request = new OAuthRequest(Verb.POST, url);

		StringBuilder payload = new StringBuilder("<entry><content>" + content + "</content>");
		if (parentId > 0)
			payload.append("<parent_id>").append(parentId).append("</parent_id>");
		payload.append("</entry>");

		request.addPayload(payload.toString());
		request.addHeader("Content-Type", "text/xml;charset=UTF-8");
		service.signRequest(token, request);
		return XmlParse.getEntry(request.send().getBody());
	}

	@Override
	public Entry createEntry(String content, int groupParam) throws IllegalArgumentException {
		assertLength(content.length());
		String url = ROOM_URL + groupParam + "/entries.xml";
		OAuthRequest request = new OAuthRequest(Verb.POST, url);
		request.addPayload("<entry><content>" + content + "</content></entry>");
		request.addHeader("Content-Type", "text/xml;charset=UTF-8");
		service.signRequest(token, request);
		return XmlParse.getEntry(request.send().getBody());
	}

	@Override
	public Entry updateEntry(int id, String content, int groupParam) throws IllegalArgumentException {
		assertLength(content.length());
		String url = ROOM_URL + groupParam + "/entries/" + id + ".xml";
		OAuthRequest request = new OAuthRequest(Verb.PUT, url);
		request.addPayload("<entry><content>" + content + "</content></entry>");
		request.addHeader("Content-Type", "text/xml;charset=UTF-8");
		service.signRequest(token, request);
		return XmlParse.getEntry(request.send().getBody());
	}

	@Override
	public Entry destroyEntry(int id, int groupParam) {
		String url = ROOM_URL + groupParam + "/entries/" + id + ".xml";
		OAuthRequest request = new OAuthRequest(Verb.DELETE, url);
		service.signRequest(token, request);
		return XmlParse.getEntry(request.send().getBody());
	}

	@Override
	public byte[] showAttachment(int id, int groupParam) throws IllegalArgumentException {
		String url = ROOM_URL + groupParam + "/entries/" + id + "/attachment";
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		service.signRequest(token, request);
		Response response = request.send();
		if (response.getCode() == 404) throw new IllegalArgumentException("Some of the arguments are invalid.");
		return response.getBody().getBytes(Charset.forName("UTF-8"));
	}

	@Override
	public List<MyGroup> getMyGroups() {
		OAuthRequest request = new OAuthRequest(Verb.GET, MY_GROUPS_URL);
		service.signRequest(token, request);
		return XmlParse.getMyGroups(request.send().getBody());
	}

	@Override
	public User verifyCredentials() {
		OAuthRequest request = new OAuthRequest(Verb.GET, USER_VERIFY_CREDENTIALS_URL);
		service.signRequest(token, request);
		return XmlParse.getUser(request.send().getBody());
	}

	@Override
	public byte[] showPicture(int groupParam, int participationId) throws IllegalArgumentException {
		String url = ROOM_URL + groupParam + "/participations/" + participationId + "/picture";
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		service.signRequest(token, request);
		Response response = request.send();
		if (response.getCode() == 404) throw new IllegalArgumentException("Some of the arguments are invalid.");
		return response.getBody().getBytes(Charset.forName("UTF-8"));
	}

	/**
	 * Add paramater to url.
	 *
	 * @param paging
	 * @param url
	 * @return url added paramater.
	 */
	private String addParamater(Paging paging, String url) {
		StringBuilder newUrl = new StringBuilder(url);

		int page = paging.getPage();
		if (page > 0) newUrl.append("page=").append(page).append("&");

		boolean flat = paging.getFlat();
		if (flat) newUrl.append("flat=").append(flat).append("&");

		String readState = paging.getReadState();
		if ("unread".equals(readState)) newUrl.append("read_state=").append(readState).append("&");

		String since = paging.getSince();
		if (since != null) newUrl.append("since=").append(since).append("&");

		return newUrl.toString();
	}

	/**
	 * Assert content length.
	 *
	 * @param length
	 * @throws IllegalArgumentException
	 */
	private void assertLength(int length) {
		if (length > 280) throw new IllegalArgumentException("Number of characters is greater than 280.");
		if (length == 0) throw new IllegalArgumentException("Number of characters is 0.");
	}
}