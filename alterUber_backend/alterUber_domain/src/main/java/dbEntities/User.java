package dbEntities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@XmlRootElement
@Table(name = "user")
public class User implements Serializable {

  @Id
  private String username;

  private String password;
  private String firstname;
  private String lastname;
  private String email;
  private String telephone;
  private String photo;

  @ManyToOne
  @JoinColumn(name = "role")
  private Role role;

  @Temporal(TemporalType.TIMESTAMP)
  private Date registrationDate;

  @Column(name = "deletedAccount", columnDefinition = "TINYINT(1) UNSIGNED NULL DEFAULT 0")
  private int deletedAccount;

  // all fields are NOT NULL
  /* default values:registrationDate = CURRENT_TIMESTAMP
                    deletedAccount = 0
  */

  public User() {}

  public User(String username,
              String password,
              String email,
              Role role) {

    this.username = username;
    this.password = password;
    this.email =  email;
    this.role = role;
    this.registrationDate = new Date();
    this.deletedAccount = 0;
  }

  public User(String username,
              String password,
              String firstname,
              String lastname,
              String email,
              String telephone,
              String photo,
              Role role) {

    this.username = username;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email =  email;
    this.telephone = telephone;
    this.photo = photo;
    this.role = role;
    this.registrationDate = new Date();
    this.deletedAccount = 0;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }


  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }


  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }


  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }


  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }


  public int getDeletedAccount() {
    return deletedAccount;
  }

  public void setDeletedAccount(int deletedAccount) {
    this.deletedAccount = deletedAccount;
  }


}
