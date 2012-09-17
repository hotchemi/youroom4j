package youroom4j.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Representing one single user status.
 *
 * @author Shintaro Katafuchi
 */
public class User {

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

	public void setId(String id) {
		this.id = Integer.parseInt(id);
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

	/**
	 * For debug.
	 *
	 * @return all fields.
	 */
	@Override
	public String toString() {
		return new StringBuilder()
		.append("created_at:").append(createdAt).append("\n")
		.append("email:").append(email).append("\n")
		.append("id:").append(id).append("\n")
		.append("last_request_at:").append(lastRequestAt).append("\n")
		.append("updated_at:").append(updatedAt).append("\n")
		.append("participations:").append(participations).append("\n")
		.toString();
	}

}