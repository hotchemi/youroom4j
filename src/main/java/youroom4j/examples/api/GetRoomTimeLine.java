package youroom4j.examples.api;

import java.util.List;
import org.scribe.model.Token;
import youroom4j.YouRoom;
import youroom4j.entity.Entry;
import youroom4j.entity.Paging;

/**
 * Sample for access room timeline api.
 *
 * @author Shintaro Katafuchi
 */
public class GetRoomTimeLine {
	public static void main(String[] args) throws Exception {
		Token accessToken = new Token("CPUO4nEXV91xJ8s6yNVO", "IAz8Wm1Sy1cht3zXsdzr0VpkYds69egi7UZUpNIR");
		YouRoom youroom = new YouRoom(accessToken);
		List<Entry> lists = youroom.getRoomTimeline(new Paging(12154));

		for (Entry entry : lists) {
			System.out.println(entry.getContent());
		}
	}
}