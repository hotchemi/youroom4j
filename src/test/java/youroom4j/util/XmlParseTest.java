package youroom4j.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import youroom4j.model.Entry;
import youroom4j.model.MyGroup;
import youroom4j.model.User;

/**
 * Test for {@link XmlParse}.
 *
 * @author Shintaro Katafuchi
 */
public class XmlParseTest {

  private static final String XML_PATH = "src/test/java/youroom4j/util/xml/";

  private static final Logger logger = Logger.getLogger("YouRoomImplTest");

  @Test
  public void getTimelineNormal() throws IOException {
    String xml = fileToString(new File(XML_PATH + "home_timeline.xml"));
    List<Entry> list = XmlParse.getTimeline(xml);
    assertNotNull(list);
    assertSame(list.size(), 7);
    for (Entry entry : list) {
      assertNotNull(entry);
      logger.info(entry.toString());
    }
  }

  @Test
  public void getRoomTimelineNormal() throws IOException {
    String xml = fileToString(new File(XML_PATH + "room_timeline.xml"));
    List<Entry> list = XmlParse.getTimeline(xml);
    assertNotNull(list);
    assertSame(list.size(), 2);
    for (Entry entry : list) {
      assertNotNull(entry);
      logger.info(entry.toString());
    }
  }

  @Test
  public void getEntryNormal() throws IOException {
    String xml = fileToString(new File(XML_PATH + "entry.xml"));
    Entry entry = XmlParse.getEntry(xml);
    assertNotNull(entry);
    logger.info(entry.toString());
  }

  @Test
  public void getMyGroupsNormal() throws IOException {
    String xml = fileToString(new File(XML_PATH + "mygroups.xml"));
    List<MyGroup> list = XmlParse.getMyGroups(xml);
    assertNotNull(list);
    assertSame(list.size(), 2);
    for (MyGroup group : list) {
      assertNotNull(group);
      logger.info(group.toString());
    }
  }

  @Test
  public void getUserNormal() throws IOException {
    String xml = fileToString(new File(XML_PATH + "credential.xml"));
    User user = XmlParse.getUser(xml);
    assertNotNull(user);
    logger.info(user.toString());
  }

  private static String fileToString(File file) throws IOException {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
      StringBuilder sb = new StringBuilder();
      int c;
      while ((c = br.read()) != -1) {
        sb.append((char) c);
      }
      return sb.toString();
    } finally {
      if (br != null)
        br.close();
    }
  }

}