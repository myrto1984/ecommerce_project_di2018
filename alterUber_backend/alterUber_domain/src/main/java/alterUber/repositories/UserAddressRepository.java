package alterUber.repositories;

import alterUber.dbEntities.User;
import alterUber.dbEntities.UserAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userAddressRepository
// CRUD refers Create, Read, Update, Delete

public interface UserAddressRepository extends CrudRepository<UserAddress, Long> {

  List<UserAddress> findAllByUser(@Param("user") User user);
}
