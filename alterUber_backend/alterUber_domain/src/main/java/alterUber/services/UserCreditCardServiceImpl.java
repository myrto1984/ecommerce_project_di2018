package alterUber.services;

import alterUber.dbEntities.User;
import alterUber.dbEntities.UserCreditCard;
import alterUber.repositories.UserCreditCardRepository;
import alterUber.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCreditCardServiceImpl implements UserCreditCardService {

  @Autowired
  private UserCreditCardRepository userCreditCardRepository;

  @Autowired
  private UserRepository userRepository;


  @Override
  public UserCreditCard addUserCreditCard(UserCreditCard userCreditCard) {
    return this.userCreditCardRepository.save(userCreditCard);
  }

  @Override
  public UserCreditCard updateUserCreditCard(UserCreditCard userCreditCard) {
    return this.userCreditCardRepository.save(userCreditCard);
  }

  @Override
  public boolean deleteUserCreditCard(UserCreditCard userCreditCard) {
    this.userCreditCardRepository.delete(userCreditCard);
    return (this.userCreditCardRepository.countCardsByNo(userCreditCard.getCardNo()) > 0);
  }

  @Override
  public List<UserCreditCard> getCreditCardsOfUser(String username) {
    User user = this.userRepository.findByUsername(username);
    return this.userCreditCardRepository.findAllByUser(user);
  }

}
