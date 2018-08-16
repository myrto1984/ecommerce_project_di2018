package alterUber.repositories;

import alterUber.dbEntities.Invoice;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called InvoiceRepository
// CRUD refers Create, Read, Update, Delete

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
