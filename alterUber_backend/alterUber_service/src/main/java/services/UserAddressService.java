package services;

import dbEntities.User;
import dbEntities.UserAddress;

import java.util.List;

public interface UserAddressService {
  UserAddress addUserAddress(UserAddress userAddress);
  UserAddress updateUserAddress(UserAddress userAddress);
  boolean deleteUserAddress(UserAddress userAddress);
  List<UserAddress> getAddressesOfUser(String username);

}
