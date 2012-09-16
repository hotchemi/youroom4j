package youroom4j;

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
import org.scribe.builder.ServiceBuilder;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import youroom4j.auth.YouRoomApi;

/**
 * Defines methods to access youRoom api.
 *
 * @author Shintaro Katafuchi
 */
public class YouRoom {
	private static final String HOME_TIMELINE_URL = "https://www.youroom.in/entries.xml?";
	private static final String ROOM_URL = "https://www.youroom.in/r/";

	private OAuthService oauthService;
	private Token accessToken;

	/**
	 * create OAuthService.
	 * @param acceeToken
	 */
	public YouRoom(Token acceessToken) {
		oauthService = Authorization.getOauthService();
		this.accessToken = acceessToken;
	}

	/** create Service and Token. */
	public YouRoom() {
		oauthService = Authorization.getOauthService();
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
	 * Get one Entry information.
	 *
	 * @param id The ID of the entry.
	 * @param groupParam The subdomain of the room include entry.
	 * @return entry
	 * @see <a href="http://apidoc.youroom.in/rest-entry-show">API Doc</a>
	 */
	public Entry showEntry(int id, int groupParam) {
		final Entry entry = new Entry();
		StringBuilder url = new StringBuilder(ROOM_URL).append(groupParam).append("/entries/").append(id).append(".xml");

		OAuthRequest request = new OAuthRequest(Verb.GET, url.toString());
		oauthService.signRequest(accessToken, request);
		Response response = request.send();

		$(response.getBody()).each(new Each() {
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

	public boolean createEntry(Entry entry, int groupParam) {
		return false;
	}

	public boolean updateEntry(Entry entry, int groupParam) {
		return false;
	}

	public boolean deleteEntry(Entry entry, int groupParam) {
		return false;
	}

	public BinaryCodec showAttachment(int id, int groupParam) {
		return null;
	}

	public List<Group> getMyGroups() {
		return null;
	}

	public List<Entry> verifyCredentials() {
		return null;
	}

	public BinaryCodec showPicture(int participationId, int groupParam) {
		return null;
	}

	/**
	 * use when get timeline information.
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
	 * Add paramaters to url for access.
	 *
	 * @param paging
	 * @param url
	 * @return proceeded url.
	 */
	private String addParamaters(Paging paging, String url) {
		StringBuilder targetUrl = new StringBuilder(url);

		int page = paging.getPage();
		if (page > 0)
			targetUrl.append("page=").append(page);

		boolean flat = paging.getFlat();
		if (flat)
			targetUrl.append("flat=").append(flat);

		String readState = paging.getReadState();
		if ("unread".equals(readState))
			targetUrl.append("read_state=").append(readState);

		String since = paging.getSince();
		if (since != null)
			targetUrl.append("since=").append(since);

		return targetUrl.toString();
	}

}