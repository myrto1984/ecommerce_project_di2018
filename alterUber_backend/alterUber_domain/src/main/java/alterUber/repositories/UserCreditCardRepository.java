package alterUber.repositories;

import alterUber.dbEntities.User;
import alterUber.dbEntities.UserCreditCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userCreditCardRepository
// CRUD refers Create, Read, Update, Delete


public interface UserCreditCardRepository extends CrudRepository<UserCreditCard, Long> {

  @Query("SELECT count(cc) FROM UserCreditCard cc WHERE cc.cardNo LIKE :cardNo")
  int countCardsByNo(@Param("cardNo") String cardNo);

  List<UserCreditCard> findAllByUser(User user);

}
