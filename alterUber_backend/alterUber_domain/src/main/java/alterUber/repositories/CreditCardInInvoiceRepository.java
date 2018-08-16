package alterUber.repositories;

import alterUber.dbEntities.CreditCardInInvoice;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called creditCardInInvoiceRepository
// CRUD refers Create, Read, Update, Delete

public interface CreditCardInInvoiceRepository extends CrudRepository<CreditCardInInvoice, Long> {
}
