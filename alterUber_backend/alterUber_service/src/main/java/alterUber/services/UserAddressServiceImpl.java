package alterUber.services;

import alterUber.dbEntities.User;
import alterUber.dbEntities.UserAddress;
import alterUber.repositories.UserAddressRepository;
import alterUber.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {

  @Autowired
  private UserAddressRepository userAddressRepository;

  @Autowired
  private UserRepository userRepository;


  @Override
  public synchronized UserAddress addUserAddress(UserAddress userAddress) {
    if (this.userAddressRepository.fetchCountExistingUserAndAddressTitle(userAddress.getUser(), userAddress.getAddressTitle()) > 0) {
      return null;
    }
    return this.userAddressRepository.save(userAddress);
  }

  @Override
  public UserAddress updateUserAddress(UserAddress userAddress) {
    return this.userAddressRepository.save(userAddress);
  }

  @Override
  public boolean deleteUserAddress(UserAddress userAddress) {
    this.userAddressRepository.delete(userAddress);
    return !this.userAddressRepository.findById(userAddress.getId()).isPresent();
  }

  @Override
  public List<UserAddress> getAddressesOfUser(String username) {
    User user = this.userRepository.findByUsername(username);
    return this.userAddressRepository.findAllByUser(user);
  }


}
