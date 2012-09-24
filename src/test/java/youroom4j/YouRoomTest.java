package youroom4j;

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
		youroom = YouRoomClient.getInstance();
	}

	@Test
	public void getHomeTimeLineTest() {
		List<Entry> list = youroom.getHomeTimeline(new Paging("a", true, 1, "unread"));
		assertSame(list.size(), 7);
		assertNotNull(list);
	}

}