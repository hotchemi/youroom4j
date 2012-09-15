package youroom4j.entity;

/**
 * Representing one single category status.
 *
 * @author Shintaro Katafuchi
 */
public class Category {

	private String name;

	private int toParam;

	private int id;

	private String color;
	
	public Category() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getToParam() {
		return toParam;
	}

	public void setToParam(String toParam) {
		this.toParam = Integer.parseInt(toParam);
	}

	public int getId() {
		return id;
	}

	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}