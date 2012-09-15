package youroom4j.examples.api;

import org.scribe.model.Token;
import youroom4j.YouRoom;

/**
 * test for YouRoom#showEntry(int id, int param).
 * @author Shintaro Katafuchi
 */
public class GetEntryShow {
	public static void main(String[] args) throws Exception {
		Token accessToken = new Token("CPUO4nEXV91xJ8s6yNVO", "IAz8Wm1Sy1cht3zXsdzr0VpkYds69egi7UZUpNIR");
		YouRoom youroom = new YouRoom(accessToken);
		System.out.println(youroom.showEntry(3075036, 23631));
	}
}
