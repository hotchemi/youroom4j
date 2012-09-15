package youroom4j.entity;

/**
 * Representing one single participation status.
 *
 * @author Shintaro Katafuchi
 */
public class Participation {

	private String name;

	private Group group;

	private int id;

	public Participation() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getId() {
		return id;
	}

	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

}