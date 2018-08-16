package alterUber.services;

import alterUber.dbEntities.User;
import alterUber.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();
    this.userRepository.findAll().forEach(e -> users.add(e));
    return users;
  }

  @Override
  public boolean getIfUsernameExists(String username) {
    return (this.userRepository.fetchExistingUsername(username) > 0);
  }

  @Override
  public User getUser(String username, String password) {
    User user = this.userRepository.findByUsername(username);
    if ( (user != null) && user.getPassword().equals(password) ) {
      return user;
    } else {
      return  null;
    }
  }

  @Override
  public synchronized boolean addUser(User user) {
    if (this.userRepository.fetchExistingUsername(user.getUsername()) > 0) {
      return false;
    }
    this.userRepository.save(user);
    return true;
  }

  @Override
  public User updateUser(User user) {
    return this.userRepository.save(user);
  }

  @Override
  public boolean deleteUser(User user) {
    this.userRepository.delete(user);
    return (this.userRepository.fetchExistingUsername(user.getUsername()) > 0);
  }


}
