package alterUber.repositories;

import alterUber.dbEntities.UserCreditCard;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userCreditCardRepository
// CRUD refers Create, Read, Update, Delete


public interface UserCreditCardRepository extends CrudRepository<UserCreditCard, Long> {

}
