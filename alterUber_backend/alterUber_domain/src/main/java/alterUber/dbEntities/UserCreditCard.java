package alterUber.dbEntities;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@Entity
@XmlRootElement
@Table(name = "user_credit_card")
public class UserCreditCard {

  @Id
  private String cardNo;

  @ManyToOne
  @JoinColumn(name = "username")
  private User user;

  private String cardType;
  private String nameOnCard;
  private String securityCode;


  // all fields are not null

  public UserCreditCard() {}

  public UserCreditCard(String cardNo,
                        User user,
                        String cardType,
                        String nameOnCard,
                        String securityCode) {
    this.cardNo = cardNo;
    this.user = user;
    this.cardType = cardType;
    this.nameOnCard = nameOnCard;
    this.securityCode = securityCode;
  }

  public String getCardNo() {
    return cardNo;
  }

  public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
  }


  public User getUser() {
    return user;
  }

  public void setUsername(User user) {
    this.user = user;
  }


  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }


  public String getNameOnCard() {
    return nameOnCard;
  }

  public void setNameOnCard(String nameOnCard) {
    this.nameOnCard = nameOnCard;
  }


  public String getSecurityCode() {
    return securityCode;
  }

  public void setSecurityCode(String securityCode) {
    this.securityCode = securityCode;
  }


}
