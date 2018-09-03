package dbEntities;


import utils.Constants;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@XmlRootElement
@Table(name = "invoice")
public class Invoice implements Serializable {

  @Id
  private String invoiceId;

  @OneToOne
  @JoinColumn(name = "tripId")
  private Trip trip;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dateIssued;

  private double chargePerKm;
  private double vat;
  private double distanceTravelled;
  private double totalCharge;

  @Column(name = "paidInCash", columnDefinition = "TINYINT(1) UNSIGNED NULL DEFAULT 1")
  private int paidInCash;


  // all fields are not null
  /* default values:dateIssued = CURRENT_TIMESTAMP, paidInCash = 1
     values from constants: chargePerKm = CHARGE_PER_KM, vat = VAT
 */

  public Invoice () {}

  public Invoice (Trip trip, double distanceTravelled, int paidInCash) {
    this.trip = trip;
    this.invoiceId = this.trip.getTripEndTime().toString() + "-" + this.trip.getId();
    this.dateIssued = new Date();
    this.chargePerKm = Constants.CHARGE_PER_KM;
    this.vat = Constants.VAT;
    this.distanceTravelled = distanceTravelled;
    this.totalCharge = this.chargePerKm * this.distanceTravelled * (1 + this.vat);
    this.paidInCash = paidInCash;
  }

  public String getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }


  public Trip getTrip() {
    return trip;
  }

  public void setTripId(Trip trip) {
    this.trip = trip;
  }


  public Date getDateIssued() {
    return dateIssued;
  }

  public void setDateIssued(Date dateIssued) {
    this.dateIssued = dateIssued;
  }


  public double getChargePerKm() {
    return chargePerKm;
  }

  public void setChargePerKm(double chargePerKm) {
    this.chargePerKm = chargePerKm;
  }


  public double getVat() {
    return vat;
  }

  public void setVat(double vat) {
    this.vat = vat;
  }


  public double getDistanceTravelled() {
    return distanceTravelled;
  }

  public void setDistanceTravelled(double distanceTravelled) {
    this.distanceTravelled = distanceTravelled;
  }


  public double getTotalCharge() {
    return totalCharge;
  }

  public void setTotalCharge(double totalCharge) {
    this.totalCharge = totalCharge;
  }


  public int getPaidInCash() {
    return paidInCash;
  }

  public void setPaidInCash(int paidInCash) {
    this.paidInCash = paidInCash;
  }

}
