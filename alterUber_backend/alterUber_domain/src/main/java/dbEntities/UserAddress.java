package dbEntities;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "user_address")
public class UserAddress {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "username")
  private User user;

  private String addressTitle;
  private String streetAndNumber;
  private String municipality;
  private String district;
  private String postalCode;
  private String gmapMarker;


  // all fields are not null

  public UserAddress() {}

  public UserAddress(User user,
                     String addressTitle,
                     String streetAndNumber,
                     String municipality,
                     String district,
                     String postalCode,
                     String gmapMarker) {
    this.user = user;
    this.addressTitle = addressTitle;
    this.streetAndNumber = streetAndNumber;
    this.municipality = municipality;
    this.district = district;
    this.postalCode = postalCode;
    this.gmapMarker = gmapMarker;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }


  public String getAddressTitle() {
    return addressTitle;
  }

  public void setAddressTitle(String addressTitle) {
    this.addressTitle = addressTitle;
  }


  public String getStreetAndNumber() {
    return streetAndNumber;
  }

  public void setStreetAndNumber(String streetAndNumber) {
    this.streetAndNumber = streetAndNumber;
  }


  public String getMunicipality() {
    return municipality;
  }

  public void setMunicipality(String municipality) {
    this.municipality = municipality;
  }


  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }


  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }


  public String getGmapMarker() {
    return gmapMarker;
  }

  public void setGmapMarker(String gmapMarker) {
    this.gmapMarker = gmapMarker;
  }

}
