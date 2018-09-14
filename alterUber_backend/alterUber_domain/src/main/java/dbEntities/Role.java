package dbEntities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Entity
@Table(name = "roles")
public class Role implements Serializable {

  @Id private String role;


  public Role() {}

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

}
