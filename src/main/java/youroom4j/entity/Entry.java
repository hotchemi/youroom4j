package youroom4j.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Representing one single entry status.
 *
 * @author Shintaro Katafuchi
 */
public class Entry extends BaseEntity {

  private List<Integer> unreadCommentIds;

  private String createdAt;

  private String updatedAt;

  private int rootId;

  private int id;

  private int level;

  private int parentId;

  private String content;

  private boolean canUpdate;

  private int descendantsCount;

  private boolean hasRead;

  /** representing participation status. */
  private Participation participation;

	/** representing attachment status. */
  private Attachment attachment;

	public Entry() {

  }

	public List<Integer> getUnreadCommentIds() {
		return unreadCommentIds;
	}

	public void setUnreadCommentIds(String unreadCommentIds) {
		this.unreadCommentIds = new ArrayList<Integer>();

		if (!isNullOrEmpty(unreadCommentIds)) {
			String[] array = unreadCommentIds.split(",");
			for(int i = 0; i < array.length; i++){
				this.unreadCommentIds.add(Integer.parseInt(array[i]));
			}
		}
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getRootId() {
		return rootId;
	}

	public void setRootId(String rootId) {
		this.rootId = Integer.parseInt(rootId);
	}

	public int getId() {
		return id;
	}

	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = Integer.parseInt(level);
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = Integer.parseInt(parentId);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isCanUpdate() {
		return canUpdate;
	}

	public void setCanUpdate(String canUpdate) {
		this.canUpdate = Boolean.valueOf(canUpdate);
	}

	public int getDescendantsCount() {
		return descendantsCount;
	}

	public void setDescendantsCount(String descendantsCount) {
		this.descendantsCount = Integer.parseInt(descendantsCount);
	}

	public Participation getParticipation() {
		return participation;
	}

	public void setParticipation(Participation participation) {
		this.participation = participation;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public boolean getHasRead() {
		return hasRead;
	}

	public void setHasRead(String hasRead) {
		this.hasRead = Boolean.valueOf(hasRead);
	}

	/**
	 * Override for debug.
	 * @return all fields.
	 */
	@Override
	public String toString() {
		return new StringBuilder()
		.append("unread_comment_ids:").append(unreadCommentIds).append("\n")
		.append("created_at:").append(createdAt).append("\n")
		.append("updated_at:").append(updatedAt).append("\n")
		.append("root_id:").append(rootId).append("\n")
		.append("level:").append(level).append("\n")
		.append("parent_id:").append(parentId).append("\n")
		.append("content:").append(content).append("\n")
		.append("can_update:").append(canUpdate).append("\n")
		.append("descendants_count:").append(descendantsCount).append("\n")
		.append("has_read:").append(hasRead).append("\n")
		.append("Participation:").append(participation).append("\n")
		.append("Attachment:").append(attachment).append("\n")
		.toString();
	}

}