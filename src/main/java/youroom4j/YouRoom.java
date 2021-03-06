package youroom4j;

import youroom4j.exception.YouRoomException;
import youroom4j.model.*;

import java.util.List;

/**
 * @author Shintaro Katafuchi
 */
public interface YouRoom {

  String HOME_TIMELINE_URL = "https://www.youroom.in/entries.xml?";

  String ROOM_URL = "https://www.youroom.in/r/";

  String MY_GROUPS_URL = "https://www.youroom.in/groups/my.xml";

  String USER_VERIFY_CREDENTIALS_URL = "https://www.youroom.in/verify_credentials.xml";

  /**
   * @param consumerKey youRoom consumer key.
   * @param consumerSecret youRoom consumer secret.
   * @param callback callback url you want.
   */
  void setOAuthConsumer(String consumerKey, String consumerSecret, String callback);

  /**
   * @param consumerKey youRoom consumer key.
   * @param consumerSecret youRoom consumer secret.
   */
  void setOAuthConsumer(String consumerKey, String consumerSecret);

  /**
   * @return authorization url.
   */
  String getAuthorizationUrl() throws YouRoomException;

  /**
   * Return AccessToken Instance.
   *
   * @param verifier verifier.
   * @return AccessToken
   */
  AccessToken getOAuthAccessToken(String verifier) throws YouRoomException;

  /**
   * @param accessToken youRoom accessToken.
   * @param accessTokenSecret youRoom accessTokenSecret.
   */
  void setOAuthAccessToken(String accessToken, String accessTokenSecret);

  /**
   * Get home timeline.
   *
   * @param paging Paging object.
   * @return list contains entry.
   * @throws IllegalArgumentException
   * @see <a href="http://apidoc.youroom.in/rest-timeline-home">API Doc</a>
   */
  List<Entry> getHomeTimeline(Paging paging) throws IllegalArgumentException;

  /**
   * Get room timeline.
   *
   * @param paging Paging pbject.
   * @return list contains entry.
   * @throws IllegalArgumentException
   * @see <a href="http://apidoc.youroom.in/rest-room-timeline">API Doc</a>
   */
  List<Entry> getRoomTimeline(Paging paging) throws IllegalArgumentException;

  /**
   * Get one single entry.
   *
   * @param id The ID of the entry.
   * @param groupParam The subdomain of the room include entry.
   * @return entry.
   * @throws IllegalArgumentException
   * @see <a href="http://apidoc.youroom.in/rest-entry-show">API Doc</a>
   */
  Entry showEntry(int id, int groupParam) throws IllegalArgumentException;

  /**
   * Create one single entry.
   *
   * @param content The text of Entry's content. Text over 280 characters will cause a 422 error to be returned from the API.
   * @param parentId Optional The id of parent entry.
   * @param groupParam The subdomain of the room include entry to create.
   * @return entry.
   * @throws IllegalArgumentException
   * @see <a href="http://apidoc.youroom.in/rest-entry-create">API Doc</a>
   */
  Entry createEntry(String content, int parentId, int groupParam) throws IllegalArgumentException;

  /**
   * Create one single entry.
   *
   * @param content The text of Entry's content. Text over 280 characters will cause a 422 error to be returned from the API.
   * @param groupParam The subdomain of the room include entry to create.
   * @return entry.
   * @throws IllegalArgumentException
   * @see <a href="http://apidoc.youroom.in/rest-entry-create">API Doc</a>
   */
  Entry createEntry(String content, int groupParam) throws IllegalArgumentException;

  /**
   * Update one single entry.
   *
   * @param id The ID of the entry to update.
   * @param content The text of Entry's content. Text over 280 characters will cause a 422 error to be returned from the API.
   * @param groupParam The subdomain of the room include entry to update.
   * @return entry.
   * @throws IllegalArgumentException
   * @see <a href="http://apidoc.youroom.in/rest-entry-update">API Doc</a>
   */
  Entry updateEntry(int id, String content, int groupParam) throws IllegalArgumentException;

  /**
   * Destroy one single entry.
   *
   * @param id The ID of the entry to destroy.
   * @param groupParam The subdomain of the room include entry to destroy.
   * @return entry.
   * @throws IllegalArgumentException
   * @see <a href="http://apidoc.youroom.in/rest-entry-detroy">API Doc</a>
   */
  Entry destroyEntry(int id, int groupParam) throws IllegalArgumentException;

  /**
   * Get attachment.
   *
   * @param id The ID of the entry.
   * @param groupParam The subdomain of the room include entry.
   * @return A binary data.
   * @throws IllegalArgumentException
   * @see <a href="http://apidoc.youroom.in/rest-attachment-show">API Doc</a>
   */
  byte[] showAttachment(int id, int groupParam) throws IllegalArgumentException;

  /**
   * Get my groups.
   *
   * @return list contains mygroup.
   * @see <a href="http://apidoc.youroom.in/rest-my-groups">API Doc</a>
   */
  List<MyGroup> getMyGroups();

  /**
   * Get User/verify_credentials.
   *
   * @return user.
   * @see <a href="http://apidoc.youroom.in/rest-user-verify-credentials">API Doc</a>
   */
  User verifyCredentials();

  /**
   * Get picture.<br/>
   * When you designate illegal participationId, API returns deafult picture.<br/>
   * https://www.youroom.in/images/default_group_picture.png
   *
   * @param groupParam The subdomain of the room include entry.
   * @param participationId The ID of the entry.
   * @return A binary data.
   * @throws IllegalArgumentException
   * @see <a href="http://apidoc.youroom.in/rest-picture-show">API Doc</a>
   */
  byte[] showPicture(int groupParam, int participationId) throws IllegalArgumentException;

}