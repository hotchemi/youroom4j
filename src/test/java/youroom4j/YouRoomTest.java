package youroom4j;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.scribe.model.Token;
import youroom4j.entity.Entry;
import youroom4j.entity.Paging;

/**
 *
 * @author katafuchi
 */
public class YouRoomTest {
	private Token token;
	private YouRoom youroom;
	private Paging paging;
	private List<Entry> list;

	@Before
	public void setUp(){
		token = new Token("CPUO4nEXV91xJ8s6yNVO", "IAz8Wm1Sy1cht3zXsdzr0VpkYds69egi7UZUpNIR");
		youroom = new YouRoom(token);
		paging = new Paging("a", true, 1, "unread");
	}

	@Test
	public void getHomeTimeLine() {
		list = youroom.getHomeTimeline(paging);
		assertSame(list.size(), 7);
		assertNotNull(list);
	}

}