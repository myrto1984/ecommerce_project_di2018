package alterUber.dbEntities;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@XmlRootElement
@Table(name = "car")
public class Car implements Serializable {

  @Id
  private String circulationNo;

  private int maxPassengers;
  private double maxLoad;
  private String manufacturer;
  private String model;
  private String colour;
  private int productionYear;

  @Temporal(TemporalType.TIMESTAMP)
  private Date registrationDate;

  @Column(name = "deleted", columnDefinition = "TINYINT(1) UNSIGNED NULL DEFAULT 0")
  private int deleted;

  @OneToMany(mappedBy = "car")
  private Collection<DriverCar> driverCars;

  // all fields are not null
  /* default values: registrationDate = CURRENT_TIMESTAMP, maxPassengers = 4, deleted = 0 */

  public Car () {}

  public Car (String circulationNo,
              int maxPassengers,
              double maxLoad,
              String manufacturer,
              String model,
              String colour,
              int productionYear) {
    this.circulationNo = circulationNo;
    this.maxPassengers = maxPassengers;
    this.maxLoad = maxLoad;
    this.manufacturer = manufacturer;
    this.model = model;
    this.colour = colour;
    this.productionYear = productionYear;
    this.registrationDate = new Date();
    this.deleted = 0;
  }

  public String getCirculationNo() {
    return circulationNo;
  }

  public void setCirculationNo(String circulationNo) {
    this.circulationNo = circulationNo;
  }


  public int getMaxPassengers() {
    return maxPassengers;
  }

  public void setMaxPassengers(int maxPassengers) {
    this.maxPassengers = maxPassengers;
  }


  public double getMaxLoad() {
    return maxLoad;
  }

  public void setMaxLoad(double maxLoad) {
    this.maxLoad = maxLoad;
  }


  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }


  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }


  public String getColour() {
    return colour;
  }

  public void setColour(String colour) {
    this.colour = colour;
  }


  public int getProductionYear() {
    return productionYear;
  }

  public void setProductionYear(int productionYear) {
    this.productionYear = productionYear;
  }


  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }


  public int getDeleted() {
    return deleted;
  }

  public void setDeleted(int deleted) {
    this.deleted = deleted;
  }

  public Collection<DriverCar> getDriverCars() {
    return driverCars;
  }

  public void setDriverCars(Collection<DriverCar> driverCars) {
    this.driverCars = driverCars;
  }
}
