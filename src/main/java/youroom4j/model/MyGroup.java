package youroom4j.model;

/**
 * Representing one single my group status.
 *
 * @author Shintaro Katafuchi
 */
public class MyGroup implements BaseGroup {

	private String createdAt;

	private int id;

	private String name;

	private boolean opened;

	private String updatedAt;

	private int toParam;

	private boolean isExpired;

	private String billing;

	public MyGroup() {

	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getOpened() {
		return opened;
	}

	public void setOpened(String opened) {
		this.opened = Boolean.valueOf(opened);
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getToParam() {
		return toParam;
	}

	public void setToParam(String toParam) {
		this.toParam = Integer.parseInt(toParam);
	}

	public boolean getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(String isExpired) {
		this.opened = Boolean.valueOf(isExpired);
	}

	public String getBilling() {
		return billing;
	}

	public void setBilling(String billing) {
		this.billing = billing;
	}

	/**
	 * For debug.
	 *
	 * @return all fields.
	 */
	@Override
	public String toString() {
		return new StringBuilder()
		.append("created_at:").append(createdAt).append("\n")
		.append("id:").append(id).append("\n")
		.append("name:").append(name).append("\n")
		.append("opened:").append(opened).append("\n")
		.append("updated_at:").append(updatedAt).append("\n")
		.append("to_param:").append(toParam).append("\n")
		.append("is_expired:").append(isExpired).append("\n")
		.append("billing:").append(billing).append("\n")
		.toString();
	}

}