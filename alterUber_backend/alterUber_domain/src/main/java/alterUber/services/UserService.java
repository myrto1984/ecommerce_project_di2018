package alterUber.services;

import alterUber.dbEntities.User;

import java.util.List;

public interface UserService {
  List<User> getAllUsers();
  boolean getIfUsernameExists(String username);
  User getUser(String username, String password);
  boolean addUser(User user);
  User updateUser(User user);
  boolean deleteUser(User user);

}
