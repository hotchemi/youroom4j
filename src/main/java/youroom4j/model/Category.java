package youroom4j.model;

import java.io.Serializable;

/**
 * Representing one single category status.
 *
 * @author Shintaro Katafuchi
 */
public class Category implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;

  private int toParam;

  private int id;

  private String color;

  public Category() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getToParam() {
    return toParam;
  }

  public void setToParam(int toParam) {
    this.toParam = toParam;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return "Categroy{" +
        "name=" + name +
        ", to_param=" + toParam +
        ", id=" + id +
        ", color=" + color + "}";
  }
}