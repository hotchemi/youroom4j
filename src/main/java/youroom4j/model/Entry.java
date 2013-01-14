package youroom4j.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representing one single entry status.
 *
 * @author Shintaro Katafuchi
 */
public class Entry implements Serializable {

  private static final long serialVersionUID = 1L;

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

  private Participation participation;

  private Attachment attachment;

  public Entry() {}

  public List<Integer> getUnreadCommentIds() {
    return Collections.unmodifiableList(unreadCommentIds);
  }

  public void setUnreadCommentIds(String unreadCommentIds) {
    this.unreadCommentIds = new ArrayList<Integer>();

    if (unreadCommentIds != null && unreadCommentIds.length() != 0) {
      String[] array = unreadCommentIds.split(",");
      for (String anArray : array) {
        this.unreadCommentIds.add(Integer.parseInt(anArray));
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

  public void setRootId(int rootId) {
    this.rootId = rootId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getParentId() {
    return parentId;
  }

  public void setParentId(int parentId) {
    this.parentId = parentId;
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

  public void setCanUpdate(boolean canUpdate) {
    this.canUpdate = canUpdate;
  }

  public int getDescendantsCount() {
    return descendantsCount;
  }

  public void setDescendantsCount(int descendantsCount) {
    this.descendantsCount = descendantsCount;
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

  public boolean isHasRead() {
    return hasRead;
  }

  public void setHasRead(boolean hasRead) {
    this.hasRead = hasRead;
  }

  @Override
  public String toString() {
    return "Entry{" +
        "unread_comment_ids=" + unreadCommentIds +
        ", created_at=" + createdAt +
        ", updated_at=" + updatedAt +
        ", root_id=" + rootId +
        ", level=" + level +
        ", parent_id=" + parentId +
        ", content=" + content +
        ", can_update=" + canUpdate +
        ", descendants_count=" + descendantsCount +
        ", has_read=" + hasRead +
        ", Participation:" + participation +
        ", Attachment=" + attachment + "}";
  }
}