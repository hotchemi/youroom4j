package youroom4j;

import java.nio.charset.Charset;
import java.util.List;
import org.scribe.builder.ServiceBuilder;

import youroom4j.model.Entry;
import youroom4j.model.Paging;
import youroom4j.model.MyGroup;
import youroom4j.model.User;
import youroom4j.util.HttpRequestUtil;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import youroom4j.auth.YouRoomApi;

/**
 * This class provides methods to access YouRoom api.
 * To get an instance of YouRoomClient you should use YouRoomBuilder.
 *
 * @author Shintaro Katafuchi
 */
public class YouRoomClient implements YouRoom {

	private OAuthService service;

	private Token token;

	private YouRoomClient() {

	}

	public OAuthService getService() {
		return service;
	}

	public void setService(OAuthService service) {
		this.service = service;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public static YouRoom getInstance() {
		return new YouRoomClient();
	}

	public void setOAuthConsumer(String consumerKey, String consumerSecret){
		this.service = new ServiceBuilder()
						.provider(YouRoomApi.class)
						.apiKey(consumerKey)
						.apiSecret(consumerSecret)
						.build();
	}

	public void setOAuthAccessToken(Token token){
		this.token = token;
	}

	/** {@inheritDoc} */
	@Override
	public List<Entry> getHomeTimeline(Paging paging) {
		OAuthRequest request = new OAuthRequest(Verb.GET, HttpRequestUtil.addParamater(paging, HOME_TIMELINE_URL));
		getService().signRequest(getToken(), request);
		return HttpRequestUtil.getTimelineProceed(request.send().getBody());
	}

	/** {@inheritDoc} */
	@Override
	public List<Entry> getRoomTimeline(Paging paging) {
		StringBuilder url = new StringBuilder(ROOM_URL).append(paging.getGroupParam()).append("/entries.xml?");
		String searchQuery = paging.getSearchQuery();
		if (searchQuery != null && searchQuery.length() != 0)
			url.append("search_query=").append(searchQuery);

		OAuthRequest request = new OAuthRequest(Verb.GET, HttpRequestUtil.addParamater(paging, url.toString()));
		getService().signRequest(getToken(), request);
		return HttpRequestUtil.getTimelineProceed(request.send().getBody());
	}

	/** {@inheritDoc} */
	@Override
	public Entry showEntry(int id, int groupParam) {
		String url = new StringBuilder(ROOM_URL).append(groupParam).append("/entries/").append(id).append(".xml").toString();
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		getService().signRequest(getToken(), request);
		return HttpRequestUtil.getEntryProceed(request.send().getBody());
	}

	/** {@inheritDoc} */
	@Override
	public Entry createEntry(String content, int parentId, int groupParam) throws Exception {
		if (content.length() > 140 || content.length() == 0)
			throw new Exception("Illegal content length.");

		String url = new StringBuilder(ROOM_URL).append(groupParam).append("/entries.xml").toString();
		OAuthRequest request = new OAuthRequest(Verb.POST, url);

		StringBuilder payload = new StringBuilder("<entry><content>").append(content).append("</content>");
		if (parentId > 0)
			payload.append("<parent_id>").append(parentId).append("</parent_id>");
		payload.append("</entry>");

		request.addPayload(payload.toString());
		request.addHeader("Content-Type", "text/xml;charset=UTF-8");
		getService().signRequest(getToken(), request);
		Response response = request.send();
		return HttpRequestUtil.getEntryProceed(response.getBody());
	}

	/** {@inheritDoc} */
	@Override
	public Entry createEntry(String content, int groupParam) throws Exception {
		if (content.length() > 140 || content.length() == 0)
			throw new Exception("Illegal content length.");

		String url = new StringBuilder(ROOM_URL).append(groupParam).append("/entries.xml").toString();
		OAuthRequest request = new OAuthRequest(Verb.POST, url);

		String payload = new StringBuilder("<entry><content>").append(content).append("</content></entry>").toString();
		request.addPayload(payload);
		request.addHeader("Content-Type", "text/xml;charset=UTF-8");
		getService().signRequest(getToken(), request);
		return HttpRequestUtil.getEntryProceed(request.send().getBody());
	}

	/** {@inheritDoc} */
	@Override
	public Entry updateEntry(int id, String content, int groupParam) throws Exception {
		if (content.length() > 140 || content.length() == 0)
			throw new Exception("illegal content length.");

		String url = new StringBuilder(ROOM_URL).append(groupParam).append("/entries/").append(id).append(".xml").toString();
		OAuthRequest request = new OAuthRequest(Verb.PUT, url);

		String payload = new StringBuilder("<entry><content>").append(content).append("</content></entry>").toString();
		request.addPayload(payload);
		request.addHeader("Content-Type", "text/xml;charset=UTF-8");
		getService().signRequest(getToken(), request);
		return HttpRequestUtil.getEntryProceed(request.send().getBody());
	}

	/** {@inheritDoc} */
	@Override
	public Entry destroyEntry(int id, int groupParam) {
		String url = new StringBuilder(ROOM_URL).append(groupParam).append("/entries/").append(id).append(".xml").toString();
		OAuthRequest request = new OAuthRequest(Verb.DELETE, url);
		getService().signRequest(getToken(), request);
		return HttpRequestUtil.getEntryProceed(request.send().getBody());
	}

	/** {@inheritDoc} */
	@Override
	public byte[] showAttachment(int id, int groupParam) {
		String url = new StringBuilder(ROOM_URL).append(groupParam).append("/entries/").append(id).append("/attachment").toString();
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		getService().signRequest(getToken(), request);
		return request.send().getBody().getBytes(Charset.forName("UTF-8"));
	}

	/** {@inheritDoc} */
	@Override
	public List<MyGroup> getMyGroups() {
		OAuthRequest request = new OAuthRequest(Verb.GET, MY_GROUPS_URL);
		getService().signRequest(getToken(), request);
		return HttpRequestUtil.getMyGroups(request.send().getBody());
	}

	/** {@inheritDoc} */
	@Override
	public List<User> verifyCredentials() {
		OAuthRequest request = new OAuthRequest(Verb.GET, User_VERIFY_CREDENTIALS);
		getService().signRequest(getToken(), request);
		return HttpRequestUtil.getUsers(request.send().getBody());
	}

	/** {@inheritDoc} */
	@Override
	public byte[] showPicture(int groupParam, int participationId) {
		String url = new StringBuilder(ROOM_URL).append(groupParam).append("/participations/").append(participationId).append("/picture").toString();
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		getService().signRequest(getToken(), request);
		return request.send().getBody().getBytes(Charset.forName("UTF-8"));
	}

}