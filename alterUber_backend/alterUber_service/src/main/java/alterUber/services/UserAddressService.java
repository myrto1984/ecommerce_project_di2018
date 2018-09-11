package alterUber.services;

import alterUber.dbEntities.User;
import alterUber.dbEntities.UserAddress;

import java.util.List;

public interface UserAddressService {
  UserAddress addUserAddress(UserAddress userAddress);
  UserAddress updateUserAddress(UserAddress userAddress);
  boolean deleteUserAddress(UserAddress userAddress);
  List<UserAddress> getAddressesOfUser(String username);

}
