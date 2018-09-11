package dbEntities;

import utils.Constants;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@XmlRootElement
@Table(name = "trip")
public class Trip implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "passenger")
  private User passenger;

  private String startPoint;
  private String destination;

  @Column(name = "noOfStops", columnDefinition = "TINYINT(2) UNSIGNED NULL DEFAULT 0")
  private int noOfStops;

  @Temporal(TemporalType.TIMESTAMP)
  private Date requestDate;

  @Column(name = "noOfPassengers", columnDefinition = "TINYINT(2) UNSIGNED NULL DEFAULT 1")
  private int noOfPassengers;

  private String luggageCat;

  @Temporal(TemporalType.TIMESTAMP)
  private Date responseDate;

  @ManyToOne
  @JoinColumn(name = "driverCar")
  private DriverCar driverCar;

  @Temporal(TemporalType.TIMESTAMP)
  private Date tripStartTime;

  @Temporal(TemporalType.TIMESTAMP)
  private Date tripEndTime;

  private int passengerRatingOfDriver;
  private int passengerRatingOfCar;
  private String passengerComments;
  private int driverRatingOfPassenger;
  private String driverComments;

  @Column(name = "cancelledByPassenger", columnDefinition = "TINYINT(1) UNSIGNED NULL DEFAULT 0")
  private int cancelledByPassenger;

  @Column(name = "cancelledByDriver", columnDefinition = "TINYINT(1) UNSIGNED NULL DEFAULT 0")
  private int cancelledByDriver;

  // not null fields: passenger, startPoint, destination, requestDate, luggageCat
  /* default values:noOfStops = 0,
                    requestDate = CURRENT_TIMESTAMP,
                    noOfPassengers = 1,
                    luggageCat = "χειραποσκευές",
                    cancelledByPassenger = 0,
                    cancelledByDriver = 0
  */

  public Trip () {}

  // constructor with default values
  public Trip (User passenger,
               String startPoint,
               String destination) {

    this.passenger = passenger;
    this.startPoint = startPoint;
    this.destination = destination;
    this.noOfStops = 0;
    this.requestDate = new Date();
    this.noOfPassengers = 1;
    this.luggageCat = Constants.LUGGAGE_VOL.HANDBAGS.toLuggageVolString();
    this.cancelledByPassenger = 0;
    this.cancelledByDriver = 0;
  }

  // constructor (default values set: cancelledByPassenger, cancelledByDriver)
  public Trip (User passenger,
               String startPoint,
               String destination,
               int noOfStops,
               Date requestDate,
               int noOfPassengers,
               String luggageCat) {

    this.passenger = passenger;
    this.startPoint = startPoint;
    this.destination = destination;
    this.noOfStops = noOfStops;
    this.requestDate = requestDate;
    this.noOfPassengers = noOfPassengers;
    this.luggageCat = luggageCat;
    this.cancelledByPassenger = 0;
    this.cancelledByDriver = 0;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public User getPassenger() {
    return passenger;
  }

  public void setPassenger(User passenger) {
    this.passenger = passenger;
  }


  public String getStartPoint() {
    return startPoint;
  }

  public void setStartPoint(String startPoint) {
    this.startPoint = startPoint;
  }


  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }


  public int getNoOfStops() {
    return noOfStops;
  }

  public void setNoOfStops(int noOfStops) {
    this.noOfStops = noOfStops;
  }


  public Date getRequestDate() {
    return requestDate;
  }

  public void setRequestDate(Date requestDate) {
    this.requestDate = requestDate;
  }


  public int getNoOfPassengers() {
    return noOfPassengers;
  }

  public void setNoOfPassengers(int noOfPassengers) {
    this.noOfPassengers = noOfPassengers;
  }


  public String getLuggageCat() {
    return luggageCat;
  }

  public void setLuggageCat(String luggageCat) {
    this.luggageCat = luggageCat;
  }


  public Date getResponseDate() {
    return responseDate;
  }

  public void setResponseDate(Date responseDate) {
    this.responseDate = responseDate;
  }


  public DriverCar getDriverCar() {
    return driverCar;
  }

  public void setDriverCar(DriverCar driverCar) {
    this.driverCar = driverCar;
  }


  public Date getTripStartTime() {
    return tripStartTime;
  }

  public void setTripStartTime(Date tripStartTime) {
    this.tripStartTime = tripStartTime;
  }


  public Date getTripEndTime() {
    return tripEndTime;
  }

  public void setTripEndTime(Date tripEndTime) {
    this.tripEndTime = tripEndTime;
  }


  public int getPassengerRatingOfDriver() {
    return passengerRatingOfDriver;
  }

  public void setPassengerRatingOfDriver(int passengerRatingOfDriver) {
    this.passengerRatingOfDriver = passengerRatingOfDriver;
  }


  public int getPassengerRatingOfCar() {
    return passengerRatingOfCar;
  }

  public void setPassengerRatingOfCar(int passengerRatingOfCar) {
    this.passengerRatingOfCar = passengerRatingOfCar;
  }


  public String getPassengerComments() {
    return passengerComments;
  }

  public void setPassengerComments(String passengerComments) {
    this.passengerComments = passengerComments;
  }


  public int getDriverRatingOfPassenger() {
    return driverRatingOfPassenger;
  }

  public void setDriverRatingOfPassenger(int driverRatingOfPassenger) {
    this.driverRatingOfPassenger = driverRatingOfPassenger;
  }


  public String getDriverComments() {
    return driverComments;
  }

  public void setDriverComments(String driverComments) {
    this.driverComments = driverComments;
  }


  public int getCancelledByPassenger() {
    return cancelledByPassenger;
  }

  public void setCancelledByPassenger(int cancelledByPassenger) {
    this.cancelledByPassenger = cancelledByPassenger;
  }


  public int getCancelledByDriver() {
    return cancelledByDriver;
  }

  public void setCancelledByDriver(int cancelledByDriver) {
    this.cancelledByDriver = cancelledByDriver;
  }


}
