package youroom4j;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import youroom4j.entity.Attachment;
import youroom4j.entity.Category;
import youroom4j.entity.Data;
import youroom4j.entity.Entry;
import youroom4j.entity.Group;
import youroom4j.entity.Paging;
import youroom4j.entity.Participation;
import youroom4j.auth.Authorization;

import org.apache.commons.codec.binary.BinaryCodec;
import org.joox.Context;
import org.joox.Each;
import static org.joox.JOOX.$;
import org.joox.Match;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import youroom4j.entity.MyGroup;
import youroom4j.entity.User;

/**
 * Have methods to access youRoom api.
 *
 * @author Shintaro Katafuchi
 */
public class YouRoom {
	private static final String HOME_TIMELINE_URL = "https://www.youroom.in/entries.xml?";
	private static final String ROOM_URL = "https://www.youroom.in/r/";
	private static final String MY_GROUPS_URL = "https://www.youroom.in/groups/my.xml";
	private static final String User_VERIFY_CREDENTIALS = "https://www.youroom.in/verify_credentials.xml";

	private OAuthService oauthService;
	private Token accessToken;

	/**
	 * Create OAuthService.
	 * @param acceessToken
	 */
	public YouRoom(Token acceessToken) {
		this.oauthService = Authorization.getOauthService();
		this.accessToken = acceessToken;
	}

	/** Create Service and Token. */
	public YouRoom() {
		this.oauthService = Authorization.getOauthService();
		this.accessToken = Authorization.getToken();
	}

	/**
	 * Get home timeline information.
	 *
	 * @param paging
	 * @return results
	 * @see <a href="http://apidoc.youroom.in/rest-timeline-home">API Doc</a>
	 */
	public List<Entry> getHomeTimeline(Paging paging) {
		return getTimelineProceed(addParamaters(paging, HOME_TIMELINE_URL));
	}

	/**
	 * Get room timeline information.
	 *
	 * @param paging
	 * @return results
	 * @see <a href="http://apidoc.youroom.in/rest-room-timeline">API Doc</a>
	 */
	public List<Entry> getRoomTimeline(Paging paging) {
		StringBuilder url = new StringBuilder(ROOM_URL).append(paging.getGroupParam()).append("/entries.xml?");
		String searchQuery = paging.getSearchQuery();
		if (searchQuery != null)
			url.append("search_query=").append(searchQuery);

		return getTimelineProceed(addParamaters(paging, url.toString()));
	}

	/**
	 * Get one single entry information.
	 *
	 * @param id The ID of the entry.
	 * @param groupParam The subdomain of the room include entry.
	 * @return entry
	 * @see <a href="http://apidoc.youroom.in/rest-entry-show">API Doc</a>
	 */
	public Entry showEntry(int id, int groupParam) {
		String url = new StringBuilder(ROOM_URL).append(groupParam).append("/entries/").append(id).append(".xml").toString();
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		oauthService.signRequest(accessToken, request);
		Response response = request.send();
		return getEntryProceed(response.getBody());
	}

	/**
	 * Post one single entry.
	 *
	 * @param content The text of Entry's content. Text over 140 characters will cause a 422 error to be returned from the API.
	 * @param parentId Optional The id of parent entry.
	 * @param groupParam The subdomain of the room include entry to create.
	 * @return entry
	 * @see <a href="http://apidoc.youroom.in/rest-entry-create">API Doc</a>
	 */
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
		oauthService.signRequest(accessToken, request);
		Response response = request.send();
		return getEntryProceed(response.getBody());
	}

	/**
	 * Post one single entry.<br>
	 * Without parentId version.
	 *
	 * @param content The text of Entry's content. Text over 140 characters will cause a 422 error to be returned from the API.
	 * @param groupParam The subdomain of the room include entry to create.
	 * @return entry
	 * @see <a href="http://apidoc.youroom.in/rest-entry-create">API Doc</a>
	 */
	public Entry createEntry(String content, int groupParam) throws Exception {
		if (content.length() > 140 || content.length() == 0)
			throw new Exception("content length is illegal.");

		String url = new StringBuilder(ROOM_URL).append(groupParam).append("/entries.xml").toString();
		OAuthRequest request = new OAuthRequest(Verb.POST, url);

		String payload = new StringBuilder("<entry><content>").append(content).append("</content></entry>").toString();
		request.addPayload(payload);
		request.addHeader("Content-Type", "text/xml;charset=UTF-8");
		oauthService.signRequest(accessToken, request);
		Response response = request.send();
		return getEntryProceed(response.getBody());
	}

	/**
	 * Update one single entry.
	 *
	 * @param id The ID of the entry to update.
	 * @param content The text of Entry's content. Text over 140 characters will cause a 422 error to be returned from the API.
	 * @param groupParam The subdomain of the room include entry to update.
	 * @return entry
	 * @see <a href="http://apidoc.youroom.in/rest-entry-update">API Doc</a>
	 */
	public Entry updateEntry(int id, String content, int groupParam) throws Exception {
		if (content.length() > 140 || content.length() == 0)
			throw new Exception("content length is illegal.");

		String url = new StringBuilder(ROOM_URL).append(groupParam).append("/entries/").append(id).append(".xml").toString();
		OAuthRequest request = new OAuthRequest(Verb.PUT, url);

		String payload = new StringBuilder("<entry><content>").append(content).append("</content></entry>").toString();
		request.addPayload(payload);
		request.addHeader("Content-Type", "text/xml;charset=UTF-8");
		oauthService.signRequest(accessToken, request);
		Response response = request.send();
		return getEntryProceed(response.getBody());
	}

	/**
	 * Destroy one single entry.
	 *
	 * @param id The ID of the entry to destroy.
	 * @param groupParam The subdomain of the room include entry to destroy.
	 * @return entry
	 * @see <a href="http://apidoc.youroom.in/rest-entry-detroy">API Doc</a>
	 */
	public Entry destroyEntry(int id, int groupParam) {
		String url = new StringBuilder(ROOM_URL).append(groupParam).append("/entries/").append(id).append(".xml").toString();
		OAuthRequest request = new OAuthRequest(Verb.DELETE, url);
		oauthService.signRequest(accessToken, request);
		Response response = request.send();
		return getEntryProceed(response.getBody());
	}

	/**
	 * Show one single attachment.
	 *
	 * @param id The ID of the entry.
	 * @param groupParam The subdomain of the room include entry.
	 * @return A binary data.
	 * @see <a href="http://apidoc.youroom.in/rest-attachment-show">API Doc</a>
	 */
	public byte[] showAttachment(int id, int groupParam) {
		String url = new StringBuilder(ROOM_URL).append(groupParam).append("/entries/").append(id).append("/attachment").toString();
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		oauthService.signRequest(accessToken, request);
		Response response = request.send();
		return response.getBody().getBytes(Charset.forName("UTF-8"));
	}

	/**
	 * Get my groups information.
	 *
	 * @return results
	 * @see <a href="http://apidoc.youroom.in/rest-my-groups">API Doc</a>
	 */
	public List<MyGroup> getMyGroups() {
		OAuthRequest request = new OAuthRequest(Verb.GET, MY_GROUPS_URL);
		oauthService.signRequest(accessToken, request);
		Response response = request.send();
		final List<MyGroup> results = new ArrayList<MyGroup>();

		$(response.getBody()).find("group").each(new Each() {
			@Override
			public void each(Context context) {
				Match status = $(context.element());
				MyGroup group = new MyGroup();
				group.setCreatedAt(status.child("created-at").text());
				group.setId(status.child("id").text());
				group.setIsExpired(status.child("is-expired").text());
				group.setName(status.child("name").text());
				group.setOpened(status.child("opened").text());
				group.setToParam(status.child("to-param").text());
				group.setUpdatedAt(status.child("updated-at").text());
				results.add(group);
			}
		});
		return results;
	}

	/**
	 * Get User/verify_credentials information.
	 *
	 * @return entry
	 * @see <a href="http://apidoc.youroom.in/rest-user-verify-credentials">API Doc</a>
	 */
	public List<User> verifyCredentials() {
		OAuthRequest request = new OAuthRequest(Verb.GET, User_VERIFY_CREDENTIALS);
		oauthService.signRequest(accessToken, request);
		Response response = request.send();
		final List<User> results = new ArrayList<User>();

		$(response.getBody()).each(new Each() {
			@Override
			public void each(Context context) {
				Match status = $(context.element());
				User user = new User();
				user.setCreatedAt(status.child("created-at").text());
				user.setEmail(status.child("email").text());
				user.setId(status.child("id").text());
				user.setLastRequestAt(status.child("last-request-at").text());
				user.setUpdatedAt(status.child("updated-at").text());

				Match participationMatch = status.find("participations>participation");
				while (participationMatch.is("participation")) {
					Participation participation = new Participation();
					participation.setAdmin(participationMatch.child("admin").text());
					participation.setCreatedAt(participationMatch.child("created-at").text());
					participation.setId(participationMatch.child("id").text());
					participation.setName(participationMatch.child("name").text());
					participation.setUpdatedAt(participationMatch.child("updated-at").text());
					participation.setApplicationAdmin(participationMatch.child("application-admin").text());

					Match groupMatch = status.find("group");
					MyGroup group = new MyGroup();
					group.setBilling(groupMatch.child("billing").text());
					group.setCreatedAt(groupMatch.child("cerated-at").text());
					group.setId(groupMatch.child("id").text());
					group.setName(groupMatch.child("name").text());
					group.setUpdatedAt(groupMatch.child("updated-at").text());
					group.setToParam(groupMatch.child("to-param").text());
					participation.setGroup(group);
					user.setParticipation(participation);

					participationMatch = participationMatch.next();
				}
				results.add(user);
			};
		});
		return results;
	}

	/**
	 * Show picture.
	 *
	 * @param groupParam The subdomain of the room include entry.
	 * @param participationId The ID of the entry.
	 * @return A binary data.
	 * @see <a href="http://apidoc.youroom.in/rest-picture-show">API Doc</a>
	 */
	public byte[] showPicture(int groupParam, int participationId) {
		String url = new StringBuilder(ROOM_URL).append(groupParam).append("/participations/").append(participationId).append("/picture").toString();
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		oauthService.signRequest(accessToken, request);
		Response response = request.send();
		return response.getBody().getBytes(Charset.forName("UTF-8"));
	}

	/**
	 * Executed when access timeline format.
	 *
	 * @param url
	 * @return results
	 * @see YouRoom#getHomeTimeline(Paging paging)
	 * @see YouRoom#getRoomTimeline(Paging paging)
	 */
	private List<Entry> getTimelineProceed(String url) {
		final List<Entry> results = new ArrayList<Entry>();
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		oauthService.signRequest(accessToken, request);
		Response response = request.send();

		$(response.getBody()).find("entry").each(new Each() {
			@Override
			public void each(Context context) {
				Match status = $(context.element());
				Entry entry = new Entry();

				Match attachmentMatch = status.child("attachment");
				if (attachmentMatch.isNotEmpty()) {
					Attachment attachment = new Attachment();
					attachment.setOriginalFilename(attachmentMatch.child("original-filename").text());

					Match dataMatch = attachmentMatch.child("data");
					if (dataMatch.isNotEmpty()) {
						Data data = new Data();
						data.setText(dataMatch.child("text").text());
						attachment.setData(data);
					}

					attachment.setContentType(attachmentMatch.child("content-type").text());
					attachment.setAttachmentType(attachmentMatch.child("attachment-type").text());
					attachment.setFileName(attachmentMatch.child("filename").text());
					entry.setAttachment(attachment);
				}

				entry.setHasRead(status.child("has-read").text());
				entry.setCreatedAt(status.child("created-at").text());
				entry.setContent(status.child("content").text());
				entry.setDescendantsCount(status.child("descendants-count").text());
				entry.setCanUpdate(status.child("can-update").text());
				entry.setUpdatedAt(status.child("updated-at").text());
				entry.setRootId(status.child("root-id").text());
				entry.setId(status.child("id").text());
				entry.setLevel(status.child("level").text());

				Match parentId = status.child("parent-id");
				if (parentId.isNotEmpty()) {
					entry.setUnreadCommentIds(parentId.text());
				}

				Match unreadComentIds = status.child("unread-comment-ids");
				if (unreadComentIds.isNotEmpty()) {
					entry.setUnreadCommentIds(unreadComentIds.text());
				}

				Match participationMatch = status.child("participation");
				if (participationMatch.isNotEmpty()) {

					Participation participation = new Participation();
					participation.setName(participationMatch.find("name").text());

					Match groupMatch = participationMatch.find("group");
					if (groupMatch.isNotEmpty()) {

						Group group = new Group();
						group.setName(groupMatch.find("name").text());
						group.setToParam(groupMatch.find("to-param").text());

						Match categoryMatch = groupMatch.find("categories>category");
						while (categoryMatch.is("category")) {
							Category category = new Category();
							category.setName(categoryMatch.child("name").text());
							category.setColor(categoryMatch.child("color").text());
							category.setToParam(categoryMatch.child("to-param").text());
							category.setId(categoryMatch.child("id").text());
							group.setCategory(category);
							categoryMatch = categoryMatch.next();
						}
						participation.setGroup(group);
					}
					participation.setId(participationMatch.find("id").text());
					entry.setParticipation(participation);
				}
				results.add(entry);
			}
		});
		return results;
	}

	/**
	 * Executed when access entry format.
	 *
	 * @param url
	 * @return results
	 * @see YouRoom#getHomeTimeline(Paging paging)
	 * @see YouRoom#getRoomTimeline(Paging paging)
	 */
	private Entry getEntryProceed(String xml) {
		final Entry entry = new Entry();
		$(xml).each(new Each() {
			@Override
			public void each(Context context) {
				Match status = $(context.element());

				Match attachmentMatch = status.child("attachment");
				if (attachmentMatch.isNotEmpty()) {

					Attachment attachment = new Attachment();
					attachment.setOriginalFilename(attachmentMatch.child("original-filename").text());

					Match dataMatch = attachmentMatch.child("data");
					if (dataMatch.isNotEmpty()) {
						Data data = new Data();
						data.setText(dataMatch.child("text").text());
						attachment.setData(data);
					}

					attachment.setContentType(attachmentMatch.child("content-type").text());
					attachment.setAttachmentType(attachmentMatch.child("attachment-type").text());
					attachment.setFileName(attachmentMatch.child("filename").text());
					entry.setAttachment(attachment);
				}

				entry.setHasRead(status.child("has-read").text());
				entry.setCreatedAt(status.child("created-at").text());
				entry.setContent(status.child("content").text());
				entry.setDescendantsCount(status.child("descendants-count").text());
				entry.setCanUpdate(status.child("can-update").text());
				entry.setUpdatedAt(status.child("updated-at").text());
				entry.setRootId(status.child("root-id").text());
				entry.setId(status.child("id").text());
				entry.setLevel(status.child("level").text());

				Match parentId = status.child("parent-id");
				if (parentId.isNotEmpty()) {
					entry.setUnreadCommentIds(parentId.text());
				}

				Match unreadComentIds = status.child("unread-comment-ids");
				if (unreadComentIds.isNotEmpty()) {
					entry.setUnreadCommentIds(unreadComentIds.text());
				}

				Match participationMatch = status.child("participation");
				if (participationMatch.isNotEmpty()) {

					Participation participation = new Participation();
					participation.setName(participationMatch.find("name").text());

					Match groupMatch = participationMatch.find("group");
					if (groupMatch.isNotEmpty()) {

						Group group = new Group();
						group.setName(groupMatch.find("name").text());
						group.setToParam(groupMatch.find("to-param").text());

						Match categoryMatch = groupMatch.find("categories>category");
						while (categoryMatch.is("category")) {
							Category category = new Category();
							category.setName(categoryMatch.child("name").text());
							category.setColor(categoryMatch.child("color").text());
							category.setToParam(categoryMatch.child("to-param").text());
							category.setId(categoryMatch.child("id").text());
							group.setCategory(category);
							categoryMatch = categoryMatch.next();
						}
						participation.setGroup(group);
					}
					participation.setId(participationMatch.find("id").text());
					entry.setParticipation(participation);
				}
			}
		});
		return entry;
	}

	/**
	 * Add paramaters to url.
	 *
	 * @param paging
	 * @param url
	 * @return resultUrl proceeded url.
	 */
	private String addParamaters(Paging paging, String url) {
		StringBuilder resultUrl = new StringBuilder(url);

		int page = paging.getPage();
		if (page > 0)
			resultUrl.append("page=").append(page);

		boolean flat = paging.getFlat();
		if (flat)
			resultUrl.append("flat=").append(flat);

		String readState = paging.getReadState();
		if ("unread".equals(readState))
			resultUrl.append("read_state=").append(readState);

		String since = paging.getSince();
		if (since != null)
			resultUrl.append("since=").append(since);

		return resultUrl.toString();
	}

}