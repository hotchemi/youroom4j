package youroom4j.impl;

import youroom4j.YouRoom;
import youroom4j.impl.YouRoomImpl;

/**
 * This class provides methods to create ready to use YouRoomImpl.
 *
 * @author Shintaro Katafuchi
 */
public final class YouRoomFactory {

	private YouRoomFactory() {}

	/** 
	 * @return new instance of YouRoomImpl.
	 */
	public static YouRoom createInstance() {
		return new YouRoomImpl();
	}
}