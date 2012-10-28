package youroom4j.impl;

import org.junit.Test;
import static org.junit.Assert.*;
import youroom4j.YouRoom;

/**
 * Test for {@link YouRoomFactory}.
 *
 * @author Shintaro Katafuchi
 */
public class YouRoomFactoryTest {
	@Test
	public void createInstance() {
		YouRoom youroom = YouRoomFactory.getInstance();
		String className = youroom.getClass().getCanonicalName();
		assertEquals(className, "youroom4j.impl.YouRoomImpl");
	}
}