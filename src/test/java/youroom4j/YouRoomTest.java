package youroom4j;

import java.lang.reflect.Method;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import youroom4j.model.Entry;
import youroom4j.model.Paging;

/**
 * Test for {@link YouRoom}.
 * @author Shintaro Katafuchi
 */
public class YouRoomTest {
	private YouRoom youroom;

	@Before
	public void setUp(){
		youroom = YouRoomFactory.getInstance();
		youroom.setOAuthConsumer("zAEMG8v9izem77IuIRTN", "6z9r7cxcfJfxmmQNzVUYs6q4hXuG9hXPr6yHQMJz");
		youroom.setOAuthAccessToken("1rkdLsxqfx88SfaSHxTZ", "ms1C50QcDVpyEiCgc3uCCoNyfEWUefJlADD86h80");
	}

	@Test
	public void getHomeTimeLine() {
		List<Entry> list = youroom.getHomeTimeline(new Paging(true, 1, "unread"));
		assertNotNull(list);
		assertSame(list.size(), 7);
		for (Entry entry : list) {
			assertNotNull(entry);
			System.out.println(entry);
		}
	}

	@Test
	public void getRoomTimeLine() {
		List<Entry> list = youroom.getRoomTimeline(new Paging(12154));
		assertNotNull(list);
		assertSame(list.size(), 7);
		for (Entry entry : list) {
			assertNotNull(entry);
			System.out.println(entry);
		}
	}

	@Test
	public void showEntry() {
		Entry entry = youroom.showEntry(3075036, 23631);
		assertNotNull(entry);
		System.out.println(entry);
	}

	@Test
	public void testAddParamater() throws Exception {
    Class<YouRoomImpl> c = YouRoomImpl.class;
		Class[] args = { Paging.class, String.class };
    Method m = c.getDeclaredMethod("addParamater", args);
    m.setAccessible(true);
    String ret = (String) m.invoke(c.newInstance(), new Paging(true, 1, "unread"), "http://youroom.in/entries.xml?");
    assertEquals("http://youroom.in/entries.xml?page=1&flat=true&read_state=unread&", ret);
	}

}