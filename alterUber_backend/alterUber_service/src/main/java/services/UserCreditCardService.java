package services;

import dbEntities.UserCreditCard;

import java.util.List;

public interface UserCreditCardService {
  UserCreditCard addUserCreditCard(UserCreditCard userCreditCard);
  UserCreditCard updateUserCreditCard(UserCreditCard userCreditCard);
  boolean deleteUserCreditCard(UserCreditCard userCreditCard);
  List<UserCreditCard> getCreditCardsOfUser(String username);

}
