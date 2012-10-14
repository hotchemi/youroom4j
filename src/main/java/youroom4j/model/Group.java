package youroom4j.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representing one single group status.
 *
 * @author Shintaro Katafuchi
 */
public class Group implements BaseGroup, Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private int toParam;

	private List<Category> categories;

	public Group() {
		categories = new ArrayList<Category>();
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

	public void setToParam(int toParam) {
		this.toParam = toParam;
	}

	public List<Category> getCategories() {
		return Collections.unmodifiableList(categories);
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setCategory(Category category) {
		this.categories.add(category);
	}

	@Override
	public String toString() {
		return "Group{" +
			"name=" + name +
			", to_param=" + toParam +
			", categories=" + categories + "}";
	}
}