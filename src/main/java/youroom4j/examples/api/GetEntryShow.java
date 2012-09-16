package youroom4j.examples.api;

import youroom4j.YouRoom;

/**
 * test for YouRoom#showEntry(int id, int param).
 *
 * @author Shintaro Katafuchi
 */
public class GetEntryShow {
	public static void main(String[] args) throws Exception {
		YouRoom youroom = new YouRoom();
		System.out.println(youroom.showEntry(3075036, 23631));
	}
}
