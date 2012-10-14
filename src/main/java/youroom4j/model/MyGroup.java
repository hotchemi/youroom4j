package youroom4j.model;

import java.io.Serializable;

/**
 * Representing one single my group status.
 *
 * @author Shintaro Katafuchi
 */
public class MyGroup implements BaseGroup, Serializable {

	private static final long serialVersionUID = 1L;

	private String createdAt;

	private int id;

	private String name;

	private boolean opened;

	private String updatedAt;

	private int toParam;

	private boolean isExpired;

	private String billing;

	public MyGroup() {}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
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

	public void setToParam(int toParam) {
		this.toParam = toParam;
	}

	public boolean getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(boolean isExpired) {
		this.opened = isExpired;
	}

	public String getBilling() {
		return billing;
	}

	public void setBilling(String billing) {
		this.billing = billing;
	}

	@Override
	public String toString() {
		return "MyGroup{" +
			"created_at=" + createdAt +
			", id=" + id +
			", name=" + name +
			", opened=" + opened +
			", updated_at=" + updatedAt +
			", to_param=" + toParam +
			", is_expired=" + isExpired +
			", billing=" + billing + "}";
	}
}