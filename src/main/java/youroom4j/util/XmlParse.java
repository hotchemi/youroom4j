package youroom4j.util;

import java.util.ArrayList;
import java.util.List;

import org.joox.Context;
import org.joox.Each;
import static org.joox.JOOX.$;
import org.joox.Match;

import youroom4j.model.Attachment;
import youroom4j.model.Category;
import youroom4j.model.Data;
import youroom4j.model.Entry;
import youroom4j.model.Group;
import youroom4j.model.MyGroup;
import youroom4j.model.Participation;
import youroom4j.model.User;

/**
 * This class provides xml parse methods.
 *
 * @author Shintaro Katafuchi
 */
public class XmlParse {

	/**
	 * Executed when access timeline format.
	 *
	 * @param xml
	 * @return results
	 * @see YouRoom#getHomeTimeline(Paging paging)
	 * @see YouRoom#getRoomTimeline(Paging paging)
	 */
	public static List<Entry> getTimelineProceed(String xml) {
		final List<Entry> results = new ArrayList<Entry>();

		$(xml).find("entry").each(new Each() {
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
	 * @param xml
	 * @return results
	 * @see YouRoom#getHomeTimeline(Paging paging)
	 * @see YouRoom#getRoomTimeline(Paging paging)
	 */
	public static Entry getEntryProceed(String xml) {
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
	 * Executed when access my group format.
	 *
	 * @param xml
	 * @return results
	 * @see YouRoom#getMyGroup
	 */
	public static List<MyGroup> getMyGroups(String xml) {
		final List<MyGroup> results = new ArrayList<MyGroup>();
		$(xml).find("group").each(new Each() {
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
	 * Executed when access user format.
	 *
	 * @param xml
	 * @return results
	 * @see YouRoom#getMyGroup
	 */
	public static List<User> getUsers(String xml) {
		final List<User> results = new ArrayList<User>();

		$(xml).each(new Each() {
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

}