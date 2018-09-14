package dbEntities;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.CascadeType.ALL;


@Entity
@Table(name = "driver_car")
public class DriverCar implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "driver")
  private User driver;

  @ManyToOne
  @JoinColumn(name = "carCircNo")
  private Car car;

  @Column(name = "deleted", columnDefinition = "TINYINT(1) UNSIGNED NULL DEFAULT 0")
  private int deleted;

/*  @OneToMany(cascade=ALL, mappedBy="driverCar", fetch = FetchType.LAZY)
  private Collection<Trip> trips = new ArrayList<>();*/

  // all fields are not null

  public DriverCar () {}

  public DriverCar (User driver, Car car) {
    this.driver = driver;
    this.car = car;
    this.deleted = 0;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public User getDriver() {
    return driver;
  }

  public void setDriver(User driver) {
    this.driver = driver;
  }


  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }


  public int getDeleted() {
    return deleted;
  }

  public void setDeleted(int deleted) {
    this.deleted = deleted;
  }

}
