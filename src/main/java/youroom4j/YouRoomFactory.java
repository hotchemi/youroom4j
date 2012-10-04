package youroom4j;

/**
 * This class provides methods to create ready to use YouRoomImpl.
 *
 * @author Shintaro Katafuchi
 * @see YouRoomImpl
 */
public class YouRoomFactory {

	private YouRoomFactory() {}

	/** 
	 * @return new instance of YouRoomImpl.
	 */
	public static YouRoom getInstance() {
		return new YouRoomImpl();
	}

}