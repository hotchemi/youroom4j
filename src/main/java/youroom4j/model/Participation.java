package youroom4j.model;

import java.io.Serializable;

/**
 * Representing one single participation status.
 *
 * @author Shintaro Katafuchi
 */
public class Participation implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;

  private BaseGroup group;

  private int id;

  private boolean admin;

  private String createdAt;

  private String updatedAt;

  private boolean applicationAdmin;

  public Participation() {}

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

  public void setId(int id) {
    this.id = id;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
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

  public void setApplicationAdmin(boolean applicationAdmin) {
    this.applicationAdmin = applicationAdmin;
  }

  @Override
  public String toString() {
    return "Participation{" +
        "name=" + name +
        ", group=" + group +
        ", id=" + id +
        ", admin=" + admin +
        ", created_at=" + createdAt +
        ", updated_at=" + updatedAt +
        ", application_admin=" + applicationAdmin + "}";
  }
}