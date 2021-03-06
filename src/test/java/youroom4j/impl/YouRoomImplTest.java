package youroom4j.impl;

import org.junit.Before;
import org.junit.Test;

import youroom4j.YouRoom;
import youroom4j.conf.PropertyUtil;
import youroom4j.model.Entry;
import youroom4j.model.MyGroup;
import youroom4j.model.Paging;
import youroom4j.model.User;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import static org.junit.Assert.*;

/**
 * Test for {@link YouRoomImpl}.
 *
 * @author Shintaro Katafuchi
 */
public class YouRoomImplTest {

  private YouRoom youroom;

  private final int groupParam = 32096;

  private final Logger logger = Logger.getLogger("YouRoomImplTest");

  @Before
  public void setUp(){
    youroom = YouRoomFactory.getInstance();
    Properties conf = PropertyUtil.load();
    youroom.setOAuthConsumer(conf.getProperty("consumerKey"), conf.getProperty("consumerSecret"));
    youroom.setOAuthAccessToken(conf.getProperty("accessToken"), conf.getProperty("accessTokenSecret"));
  }

  @Test
  public void getHomeTimeLineNormal() {
    List<Entry> list = youroom.getHomeTimeline(new Paging(true, 1, "unread"));
    assertNotNull(list);
    assertSame(list.size(), 7);
    for (Entry entry : list) {
      assertNotNull(entry);
      logger.info(entry.toString());
    }
  }

  @Test
  public void getHomeTimeLineIllegalPage() {
    List<Entry> list = youroom.getHomeTimeline(/* Illegal paramater */ new Paging(false, 100, "read"));
    assertNotNull(list);
    assertSame(list.size(), 0);
  }

  @Test
  public void getRoomTimeLineNormal() {
    List<Entry> list = youroom.getRoomTimeline(new Paging(groupParam));
    assertNotNull(list);
    assertSame(list.size(), 7);
    for (Entry entry : list) {
      assertNotNull(entry);
      logger.info(entry.toString());
    }
  }

  @Test
  public void getRoomTimeLineIllegalPage() {
    Paging paging = new Paging();
    paging.setGroupParam(groupParam);
    // illegal
    paging.setPage(100);
    List<Entry> list = youroom.getRoomTimeline(paging);
    assertNotNull(list);
    assertSame(list.size(), 0);
  }

  @Test(expected= IllegalArgumentException.class)
  public void getRoomTimeLineIllegalGroupParam() {
    Paging paging = new Paging();
    // illegal
    paging.setGroupParam(00000);
    youroom.getRoomTimeline(paging);
  }

  @Test
  public void showEntryNormal() {
    Entry entry = youroom.showEntry(3192436, groupParam);
    assertNotNull(entry);
    logger.info(entry.toString());
  }

  @Test(expected= IllegalArgumentException.class)
  public void showEntryIllegalGroupParam() {
    youroom.showEntry(/* illegal */ 00000, 00000);
  }

  @Test
  public void createEntryNormal() {
    Entry entry = youroom.createEntry("test from youRoom4j.", groupParam);
    assertNotNull(entry);
    logger.info(entry.toString());

    Entry child = youroom.createEntry("parent entry. test from youRoom4j.", entry.getId(), groupParam);
    assertNotNull(child);
    logger.info(child.toString());
  }

  @Test(expected= IllegalArgumentException.class)
  public void createEntryIllegalGroupParam() {
    youroom.createEntry("test from youRoom4j.", /* illegal */ 00000);
  }

  @Test(expected= IllegalArgumentException.class)
  public void createEntryIllegalParentId() {
    youroom.createEntry("test from youRoom4j.", /* illegal */ 4, groupParam);
  }

  @Test(expected= IllegalArgumentException.class)
  public void createEntryWithZero() {
    youroom.createEntry("", groupParam);
  }

  @Test(expected= IllegalArgumentException.class)
  public void createEntryWith281() {
    youroom.createEntry("youroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyouroom4jyo", groupParam);
  }

  @Test
  public void updateEntryNormal() {
    Entry entry = youroom.createEntry("test from youRoom4j.", groupParam);
    Entry update = youroom.updateEntry(entry.getRootId(), "update from youRoom4j.", groupParam);
    assertNotNull(update);
    logger.info(update.toString());
  }

  @Test(expected= IllegalArgumentException.class)
  public void updateEntryIllegalgroupParam() {
    Entry entry = youroom.createEntry("test from youRoom4j.", groupParam);
    youroom.updateEntry(entry.getRootId(), "update from youRoom4j.", 00000);
  }

  @Test(expected= IllegalArgumentException.class)
  public void updateEntryIllegalParentId() {
    youroom.updateEntry(999, "update from youRoom4j.", groupParam);
  }

  @Test
  public void destroyEntryNormal() {
    Entry entry = youroom.createEntry("test from youRoom4j.", groupParam);
    Entry destroy = youroom.destroyEntry(entry.getRootId(), groupParam);
    assertNotNull(destroy);
    logger.info(destroy.toString());
  }

  @Test(expected= IllegalArgumentException.class)
  public void destroyEntryIllegalGroupParam() {
    Entry entry = youroom.createEntry("test from youRoom4j.", groupParam);
    youroom.destroyEntry(entry.getRootId(), 00000);
  }

  @Test(expected= IllegalArgumentException.class)
  public void destroyEntryIllegalId() {
    youroom.destroyEntry(000, groupParam);
  }

  @Test(expected= IllegalArgumentException.class)
  public void destroyEntryAllIllegal() {
    youroom.destroyEntry(00000, 00000);
  }

  @Test
  public void showAttachmentNormal() {
    byte[] attachment = youroom.showAttachment(3155703, groupParam);
    assertNotNull(attachment);
  }

  @Test(expected= IllegalArgumentException.class)
  public void showAttachmentIllegalGroupParam() {
    youroom.showAttachment(3155703, 00000);
  }

  @Test(expected= IllegalArgumentException.class)
  public void showAttachmentIllegalId() {
    youroom.showAttachment(000000, groupParam);
  }

  @Test
  public void getMyGroupsNormal() {
    List<MyGroup> groups = youroom.getMyGroups();
    assertNotNull(groups);
    assertSame(groups.size(), 1);
    for (MyGroup group : groups) {
      logger.info(group.toString());
    }
  }

  @Test
  public void verifyCredentialsNormal() {
    User user = youroom.verifyCredentials();
    assertNotNull(user);
    logger.info(user.toString());
  }

  @Test
  public void showPictureNormal() {
    byte[] picture = youroom.showPicture(groupParam, 3155703);
    assertNotNull(picture);
  }

  @Test(expected= IllegalArgumentException.class)
  public void showPictureIllegalGroupParam() {
    youroom.showPicture(00000, 3155703);
  }

  @Test
  public void showPictureIllegalParticipationId() {
    byte[] picture = youroom.showPicture(groupParam, 00000);
    assertNotNull(picture);
  }

  @Test
  public void addParamaterNormal() throws Exception {
    Class<YouRoomImpl> c = YouRoomImpl.class;
    Class[] args = {Paging.class, String.class};
    Method m = c.getDeclaredMethod("addParamater", args);
    m.setAccessible(true);
    String ret = (String)m.invoke(c.newInstance(), new Paging(true, 1, "unread"), "http://youroom.in/entries.xml?");
    assertEquals("http://youroom.in/entries.xml?page=1&flat=true&read_state=unread&", ret);
  }

}