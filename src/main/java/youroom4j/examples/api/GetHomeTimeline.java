package youroom4j.examples.api;

import java.util.List;
import org.scribe.model.Token;
import youroom4j.YouRoom;
import youroom4j.entity.Entry;
import youroom4j.entity.Paging;

/**
 * Sample for access hometimeline api.
 *
 * @author Shintaro Katafuchi
 */
public class GetHomeTimeline {
	public static void main(String[] args) throws Exception {
		YouRoom youroom = new YouRoom();
		List<Entry> lists = youroom.getHomeTimeline(new Paging(null, true, 1, "unread"));

		for (Entry entry : lists) {
			System.out.println(entry);
		}
	}
}