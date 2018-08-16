package alterUber.repositories;

import alterUber.dbEntities.UserAddress;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userAddressRepository
// CRUD refers Create, Read, Update, Delete

public interface UserAddressRepository extends CrudRepository<UserAddress, Long> {

}
