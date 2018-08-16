package alterUber.dbEntities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;


@Entity
@XmlRootElement
@Table(name = "roles")
public class Role implements Serializable {

  @Id private String role;

  @OneToMany(mappedBy = "role")
  private Collection<User> users;


  public Role() {}

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

}
