package youroom4j.model;

import java.io.Serializable;

/**
 * Representing one single paging status.
 *
 * @author Shintaro Katafuchi
 */
public class Paging implements Serializable {

  private static final long serialVersionUID = 1L;

  /** The subdomain of the room include entry to destroy. */
  private int groupParam;

  /** Bigining time of fetch entries. Use the RFC 3339 timestamp format. For example: 2005-08-09T10:57:00-08:00. */
  private String since;

  /** Keyword for search entries. */
  private String searchQuery;

  /** If given "true", response is include topics and comments and sorted by created_at. */
  private boolean flat;

  /** Patination entries parameter. */
  private int page;

  /** If given "unread", response is include only unread topics. */
  private String readState;

  public Paging() {}

  public Paging(int groupParam) {
    this.groupParam = groupParam;
  }

  public Paging(boolean flat, int page, String readState) {
    this.flat = flat;
    this.page = page;
    this.readState = readState;
  }

  public Paging(String since, boolean flat, int page, String readState) {
    this(flat, page, readState);
    this.since = since;
  }

  public Paging(int groupParam, String since, String searchQuery, boolean flat, int page, String readState) {
    this(since, flat, page, readState);
    this.groupParam = groupParam;
    this.searchQuery = searchQuery;
  }

  public int getGroupParam() {
    return groupParam;
  }

  public void setGroupParam(int groupParam) {
    this.groupParam = groupParam;
  }

  public String getSince() {
    return since;
  }

  public void setSince(String since) {
    this.since = since;
  }

  public String getSearchQuery() {
    return searchQuery;
  }

  public void setSearchQuery(String searchQuery) {
    this.searchQuery = searchQuery;
  }

  public boolean getFlat() {
    return flat;
  }

  public void setFlat(boolean flat) {
    this.flat = flat;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public String getReadState() {
    return readState;
  }

  public void setReadState(String readState) {
    this.readState = readState;
  }

  @Override
  public String toString() {
    return "Paging{" +
        "group_param=" + groupParam +
        ", since=" + since +
        ", search_query=" + searchQuery +
        ", flat=" + flat +
        ", page=" + page +
        ", read_stat=" + readState + "}";
  }
}