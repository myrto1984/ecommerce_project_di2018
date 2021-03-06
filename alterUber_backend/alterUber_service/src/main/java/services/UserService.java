package services;

import dbEntities.User;

import java.util.List;

public interface UserService {
  List<User> getAllUsers();
  List<User> getUsersByRole(String role);
  boolean getIfUsernameExists(String username);
  User getUser(String username, String password);
  User addUser(User user);
  User updateUser(User user);
  boolean deleteUser(User user);

}
