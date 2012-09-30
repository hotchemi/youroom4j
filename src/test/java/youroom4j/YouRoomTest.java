package youroom4j;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import youroom4j.conf.LoadProperty;
import youroom4j.model.Entry;
import youroom4j.model.MyGroup;
import youroom4j.model.Paging;
import youroom4j.model.User;

/**
 * Test for {@link YouRoom}.
 *
 * @author Shintaro Katafuchi
 */
public class YouRoomTest {

	private YouRoom youroom;

	private int groupParam = 32096;

	private static final Logger logger = Logger.getLogger("SampleLogging");

	@Before
	public void setUp(){
		youroom = YouRoomFactory.getInstance();
		Map<String ,String> map = LoadProperty.loadConf();
		youroom.setOAuthConsumer(map.get("consumerKey"), map.get("consumerSecret"));
		youroom.setOAuthAccessToken(map.get("accessToken"), map.get("accessTokenSecret"));
	}

	@Test
	public void testGetHomeTimeLine() {
		List<Entry> list = youroom.getHomeTimeline(new Paging(true, 1, "unread"));
		assertNotNull(list);
		assertSame(list.size(), 7);

		for (Entry entry : list) {
			assertNotNull(entry);
			logger.info(entry.toString());
		}
	}

	@Test
	public void testGetRoomTimeLine() {
		List<Entry> list = youroom.getRoomTimeline(new Paging(groupParam));
		assertNotNull(list);
		assertSame(list.size(), 7);
		for (Entry entry : list) {
			assertNotNull(entry);
			logger.info(entry.toString());
		}
	}

	@Test
	public void testShowEntry() {
		Entry entry = youroom.showEntry(3137082, groupParam);
		assertNotNull(entry);
		logger.info(entry.toString());
	}

	@Test(expected= org.w3c.dom.DOMException.class)
	public void testShowEntryFail() {
		// illegal value
		Entry entry = youroom.showEntry(111111, 1111);
	}

	@Test
	public void testCreateEntry() {
		Entry entry = youroom.createEntry("test from JUnit", groupParam);
		assertNotNull(entry);
		logger.info(entry.toString());
	}

	@Test
	public void testUpdateEntry() {
		Entry entry = youroom.createEntry("test from JUnit.", groupParam);
		assertNotNull(entry);
		logger.info(entry.toString());
		int createEntryId = entry.getRootId();

		entry = youroom.updateEntry(createEntryId, "update from JUnit.", groupParam);
		assertNotNull(entry);
		logger.info(entry.toString());
	}

	@Test
	public void testDestroyEntry() {
		Entry entry = youroom.createEntry("test from junit", groupParam);
		assertNotNull(entry);
		logger.info(entry.toString());
		int createEntryId = entry.getRootId();

		entry = youroom.destroyEntry(createEntryId, groupParam);
		assertNotNull(entry);
		logger.info(entry.toString());
	}

	@Test
	public void testShowAttachment() {
		byte[] attachment = youroom.showAttachment(3155703, groupParam);
		assertNotNull(attachment);
		for (int i = 0, l = attachment.length; i < l; i++) {
			logger.info(String.valueOf(attachment[i]));
		}
	}

	@Test
	public void testGetMyGroups() {
		List<MyGroup> groups = youroom.getMyGroups();
		assertNotNull(groups);
		assertSame(groups.size(), 1);
		for (MyGroup group : groups) {
			logger.info(group.toString());
		}
	}

	@Test
	public void testVerifyCredentials() {
		User user = youroom.verifyCredentials();
		assertNotNull(user);
		logger.info(user.toString());
	}

	@Test
	public void testShowPicture() {
		byte[] picture = youroom.showPicture(groupParam, 3155703);
		assertNotNull(picture);
		for (int i = 0, l = picture.length; i < l; i++) {
			logger.info(String.valueOf(picture[i]));
		}
	}

	@Test
	public void testAddParamater() throws Exception {
    Class<YouRoomImpl> c = YouRoomImpl.class;
		Class[] args = {Paging.class, String.class};
    Method m = c.getDeclaredMethod("addParamater", args);
    m.setAccessible(true);
    String ret = (String)m.invoke(c.newInstance(), new Paging(true, 1, "unread"), "http://youroom.in/entries.xml?");
    assertEquals("http://youroom.in/entries.xml?page=1&flat=true&read_state=unread&", ret);
	}

}