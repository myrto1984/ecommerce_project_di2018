package repositories;

import dbEntities.User;
import dbEntities.UserAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userAddressRepository
// CRUD refers Create, Read, Update, Delete

public interface UserAddressRepository extends CrudRepository<UserAddress, Long> {

  List<UserAddress> findAllByUser(User user);

  @Query("SELECT count(ua) FROM UserAddress ua WHERE ua.user = :user AND ua.addressTitle = :addressTitle")
  int fetchCountExistingUserAndAddressTitle(@Param("user") User user, @Param("addressTitle") String addressTitle);
}
