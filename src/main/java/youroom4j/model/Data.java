package youroom4j.model;

import java.io.Serializable;

/**
 * Representing one single data status.
 *
 * @author Shintaro Katafuchi
 */
public class Data implements Serializable {

  private static final long serialVersionUID = 1L;

  private String text;

  public Data() {
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  @Override
  public String toString() {
    return "Data{text=" + text + "}";
  }
}