package youroom4j.model;

/**
 * Representing one single participation status.
 *
 * @author Shintaro Katafuchi
 */
public class Participation {

	private String name;

	private BaseGroup group;

	private int id;

	private boolean admin;

	private String createdAt;

	private String updatedAt;

	private boolean applicationAdmin;

	public Participation() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BaseGroup getGroup() {
		return group;
	}

	public void setGroup(BaseGroup group) {
		this.group = group;
	}

	public int getId() {
		return id;
	}

	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = Boolean.valueOf(admin);
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isApplicationAdmin() {
		return applicationAdmin;
	}

	public void setApplicationAdmin(String applicationAdmin) {
		this.applicationAdmin = Boolean.valueOf(applicationAdmin);
	}

	/**
	 * For debug.
	 *
	 * @return all fields.
	 */
	@Override
	public String toString() {
		return new StringBuilder()
		.append("name:").append(name).append("\n")
		.append("group:").append(group).append("\n")
		.append("id:").append(id).append("\n")
		.append("admin:").append(admin).append("\n")
		.append("created_at:").append(createdAt).append("\n")
		.append("updated_at:").append(updatedAt).append("\n")
		.append("application_admin:").append(applicationAdmin).append("\n")
		.toString();
	}

}