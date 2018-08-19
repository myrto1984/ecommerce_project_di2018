package alterUber.dbEntities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity @IdClass(CreditCardInInvoice.class)
@XmlRootElement
@Table(name = "credit_card_in_invoice")
public class CreditCardInInvoice implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "cardNo")
  private UserCreditCard card;

  @Id
  @ManyToOne
  @JoinColumn(name = "invoiceId")
  private Invoice invoice;


  // all fields are not null

  public CreditCardInInvoice () {}

  public CreditCardInInvoice (UserCreditCard card, Invoice invoice) {
    this.card = card;
    this.invoice = invoice;
  }


  public UserCreditCard getCard() {
    return card;
  }

  public void setCard(UserCreditCard card) {
    this.card = card;
  }


  public Invoice getInvoice() {
    return invoice;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

}
