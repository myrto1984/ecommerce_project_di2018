package alterUber.repositories;

import alterUber.dbEntities.Invoice;
import alterUber.dbEntities.Trip;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called InvoiceRepository
// CRUD refers Create, Read, Update, Delete

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

  Invoice findByTrip(Trip trip);
  List<Invoice> findAllByPaidInCash(int paidInCash);

  @Query("SELECT count(i) FROM Invoice i WHERE i.invoiceId = :invoiceId")
  int fetchCountExistingById(@Param("invoiceId") String invoiceId);

  @Query("SELECT count(i) FROM Invoice i WHERE i.trip = :trip")
  int fetchCountExistingInvoiceByTrip(@Param("trip") Trip trip);

}
