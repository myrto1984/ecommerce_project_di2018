package alterUber.repositories;

import alterUber.dbEntities.CreditCardInInvoice;
import alterUber.dbEntities.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called creditCardInInvoiceRepository
// CRUD refers Create, Read, Update, Delete

public interface CreditCardInInvoiceRepository extends CrudRepository<CreditCardInInvoice, Long> {

  CreditCardInInvoice findByInvoice(Invoice invoice);
  List<CreditCardInInvoice> findByCardNo(String cardNo);

  @Query("SELECT count(cci) FROM CreditCardInInvoice cci WHERE cci.invoice = :invoice")
  int fetchCountExistingInvoice(@Param("invoice") Invoice invoice);

}
