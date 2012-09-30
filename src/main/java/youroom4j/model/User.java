package youroom4j.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representing one single user status.
 *
 * @author Shintaro Katafuchi
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String createdAt;

	private String email;

	private int id;

	private String lastRequestAt;

	private String updatedAt;

	private List<Participation> participations;

	public User() {
		participations = new ArrayList<Participation>();
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastRequestAt() {
		return lastRequestAt;
	}

	public void setLastRequestAt(String lastRequestAt) {
		this.lastRequestAt = lastRequestAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	public void setParticipation(Participation participation) {
		this.participations.add(participation);
	}

	@Override
	public String toString() {
		return "User{" +
						"created_at=" + createdAt +
						", email=" + email +
						", id=" + id +
						", last_request_at=" + lastRequestAt +
						", updated_at=" + updatedAt +
						", participations=" + participations + "}";
	}

}