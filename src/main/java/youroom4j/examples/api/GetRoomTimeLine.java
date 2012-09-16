package youroom4j.examples.api;

import java.util.List;
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
		YouRoom youroom = new YouRoom();
		List<Entry> lists = youroom.getRoomTimeline(new Paging(12154));

		for (Entry entry : lists) {
			System.out.println(entry.getContent());
		}
	}
}