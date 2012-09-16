package youroom4j.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Representing one single group status.
 *
 * @author Shintaro Katafuchi
 */
public class Group {

	private String name;

	private int toParam;

	private List<Category> categories;

	public Group() {

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

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setCategory(Category category) {
		categories = new ArrayList<Category>();
		this.categories.add(category);
	}

	/**
	 * Override for debug.
	 *
	 * @return all fields.
	 */
	@Override
	public String toString() {
		return new StringBuilder()
		.append("name:").append(name).append("\n")
		.append("to_param:").append(toParam).append("\n")
		.append("categories:").append(categories).append("\n")
		.toString();
	}

}