package youroom4j.impl;

import youroom4j.YouRoom;

/**
 * This class provides methods to create ready to use YouRoomImpl.
 *
 * @author Shintaro Katafuchi
 */
public final class YouRoomFactory {

  private YouRoomFactory() {

  }

  /**
   * Get YouRoomImpl Instance.
   * @return new instance of YouRoomImpl.
   */
  public static YouRoom getInstance() {
    return new YouRoomImpl();
  }

}