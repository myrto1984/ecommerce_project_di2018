package alterUber.repositories;

import alterUber.dbEntities.Role;
import alterUber.dbEntities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {

  @Query("SELECT count(u) FROM User u WHERE u.username LIKE :username")
  int fetchExistingUsername(@Param("username") String username);

  User findByUsername(String username);

  List<User> findAllByRole(Role role);
}