package youroom4j.util;

import java.util.ArrayList;
import java.util.List;

import org.joox.Context;
import org.joox.Each;
import static org.joox.JOOX.$;
import org.joox.Match;
import org.w3c.dom.DOMException;

import youroom4j.model.Attachment;
import youroom4j.model.Category;
import youroom4j.model.Data;
import youroom4j.model.Entry;
import youroom4j.model.Group;
import youroom4j.model.MyGroup;
import youroom4j.model.Participation;
import youroom4j.model.User;

/**
 * This class provides methods to parse xml.
 *
 * @author Shintaro Katafuchi
 */
public final class XmlParse {

	private XmlParse() {}

	/**
	 * Parse timeline xml.
	 *
	 * @param xml
	 * @return results
	 * @throws IllegalArgumentException
	 */
	public static List<Entry> getTimeline(String xml) throws IllegalArgumentException {
		final List<Entry> results = new ArrayList<Entry>();
		try {
			$(xml).find("entry").each(new Each() {
				@Override
				public void each(Context context) {
					Entry entry = new Entry();
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

					entry.setHasRead(Boolean.valueOf(status.child("has-read").text()));
					entry.setCreatedAt(status.child("created-at").text());
					entry.setContent(status.child("content").text());
					entry.setDescendantsCount(Integer.parseInt(status.child("descendants-count").text()));
					entry.setCanUpdate(Boolean.valueOf(status.child("can-update").text()));
					entry.setUpdatedAt(status.child("updated-at").text());
					entry.setRootId(Integer.parseInt(status.child("root-id").text()));
					entry.setId(Integer.parseInt(status.child("id").text()));
					entry.setLevel(Integer.parseInt(status.child("level").text()));

					Match parentId = status.child("parent-id");
					if (parentId.isNotEmpty())
						entry.setUnreadCommentIds(parentId.text());

					Match unreadComentIds = status.child("unread-comment-ids");
					if (unreadComentIds.isNotEmpty())
						entry.setUnreadCommentIds(unreadComentIds.text());

					Match participationMatch = status.child("participation");
					if (participationMatch.isNotEmpty()) {
						Participation participation = new Participation();
						participation.setName(participationMatch.find("name").text());

						Match groupMatch = participationMatch.find("group");
						if (groupMatch.isNotEmpty()) {
							Group group = new Group();
							group.setName(groupMatch.find("name").text());
							group.setToParam(Integer.parseInt(groupMatch.find("to-param").text()));

							Match categoryMatch = groupMatch.find("categories>category");
							while (categoryMatch.is("category")) {
								Category category = new Category();
								category.setName(categoryMatch.child("name").text());
								category.setColor(categoryMatch.child("color").text());
								category.setToParam(Integer.parseInt(categoryMatch.child("to-param").text()));
								category.setId(Integer.parseInt(categoryMatch.child("id").text()));
								group.setCategory(category);
								categoryMatch = categoryMatch.next();
							}
							participation.setGroup(group);
						}
						participation.setId(Integer.parseInt(participationMatch.find("id").text()));
						entry.setParticipation(participation);
					}
					results.add(entry);
				}
			});
		} catch (DOMException e) {
			throw new IllegalArgumentException("Some of the arguments are invalid and an invalid illegal xml character was specified.");
		}
		return results;
	}

	/**
	 * Parse entry xml.
	 *
	 * @param xml
	 * @return results
	 * @throws IllegalArgumentException
	 */
	public static Entry getEntry(String xml) throws IllegalArgumentException {
		final Entry entry = new Entry();
		try {
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

					entry.setHasRead(Boolean.valueOf(status.child("has-read").text()));
					entry.setCreatedAt(status.child("created-at").text());
					entry.setContent(status.child("content").text());
					entry.setDescendantsCount(Integer.parseInt(status.child("descendants-count").text()));
					entry.setCanUpdate(Boolean.valueOf(status.child("can-update").text()));
					entry.setUpdatedAt(status.child("updated-at").text());
					entry.setRootId(Integer.parseInt(status.child("root-id").text()));
					entry.setId(Integer.parseInt(status.child("id").text()));
					entry.setLevel(Integer.parseInt(status.child("level").text()));

					Match parentId = status.child("parent-id");
					if (parentId.isNotEmpty())
						entry.setUnreadCommentIds(parentId.text());

					Match unreadComentIds = status.child("unread-comment-ids");
					if (unreadComentIds.isNotEmpty())
						entry.setUnreadCommentIds(unreadComentIds.text());

					Match participationMatch = status.child("participation");
					if (participationMatch.isNotEmpty()) {
						Participation participation = new Participation();
						participation.setName(participationMatch.find("name").text());

						Match groupMatch = participationMatch.find("group");
						if (groupMatch.isNotEmpty()) {
							Group group = new Group();
							group.setName(groupMatch.find("name").text());
							group.setToParam(Integer.parseInt(groupMatch.find("to-param").text()));

							Match categoryMatch = groupMatch.find("categories>category");
							while (categoryMatch.is("category")) {
								Category category = new Category();
								category.setName(categoryMatch.child("name").text());
								category.setColor(categoryMatch.child("color").text());
								category.setToParam(Integer.parseInt(categoryMatch.child("to-param").text()));
								category.setId(Integer.parseInt(categoryMatch.child("id").text()));
								group.setCategory(category);
								categoryMatch = categoryMatch.next();
							}
							participation.setGroup(group);
						}
						participation.setId(Integer.parseInt(participationMatch.find("id").text()));
						entry.setParticipation(participation);
					}
				}
			});
		} catch (DOMException e) {
			throw new IllegalArgumentException("Some of the arguments are invalid and an invalid illegal xml character was specified.");
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("the parentId is invalid.");
		}
		return entry;
	}

	/**
	 * Parse my group xml.
	 *
	 * @param xml
	 * @return results
	 */
	public static List<MyGroup> getMyGroups(String xml) {
		final List<MyGroup> results = new ArrayList<MyGroup>();
		$(xml).find("group").each(new Each() {
			@Override
			public void each(Context context) {
				Match status = $(context.element());
				MyGroup group = new MyGroup();
				group.setCreatedAt(status.child("created-at").text());
				group.setId(Integer.parseInt(status.child("id").text()));
				group.setIsExpired(Boolean.valueOf(status.child("is-expired").text()));
				group.setName(status.child("name").text());
				group.setOpened(Boolean.valueOf(status.child("opened").text()));
				group.setToParam(Integer.parseInt(status.child("to-param").text()));
				group.setUpdatedAt(status.child("updated-at").text());
				results.add(group);
			}
		});
		return results;
	}

	/**
	 * Parse user xml.
	 *
	 * @param xml
	 * @return results
	 */
	public static User getUser(String xml) {
		final User user = new User();
		$(xml).each(new Each() {
			@Override
			public void each(Context context) {
				Match status = $(context.element());
				user.setCreatedAt(status.child("created-at").text());
				user.setEmail(status.child("email").text());
				user.setId(Integer.parseInt(status.child("id").text()));
				user.setLastRequestAt(status.child("last-request-at").text());
				user.setUpdatedAt(status.child("updated-at").text());
				Match participationMatch = status.find("participations>participation");
				while (participationMatch.is("participation")) {
					Participation participation = new Participation();
					participation.setAdmin(Boolean.valueOf(participationMatch.child("admin").text()));
					participation.setCreatedAt(participationMatch.child("created-at").text());
					participation.setId(Integer.parseInt(participationMatch.child("id").text()));
					participation.setName(participationMatch.child("name").text());
					participation.setUpdatedAt(participationMatch.child("updated-at").text());
					participation.setApplicationAdmin(Boolean.valueOf(participationMatch.child("application-admin").text()));
					Match groupMatch = status.find("group");
					MyGroup group = new MyGroup();
					group.setBilling(groupMatch.child("billing").text());
					group.setCreatedAt(groupMatch.child("cerated-at").text());
					group.setId(Integer.parseInt(groupMatch.child("id").text()));
					group.setName(groupMatch.child("name").text());
					group.setUpdatedAt(groupMatch.child("updated-at").text());
					group.setToParam(Integer.parseInt(groupMatch.child("to-param").text()));
					participation.setGroup(group);
					user.setParticipation(participation);
					participationMatch = participationMatch.next();
				}
			};
		});
		return user;
	}
}