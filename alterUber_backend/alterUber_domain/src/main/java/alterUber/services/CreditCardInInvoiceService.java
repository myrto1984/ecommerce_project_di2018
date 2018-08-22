package alterUber.services;

import alterUber.dbEntities.CreditCardInInvoice;
import alterUber.dbEntities.Invoice;

import java.util.List;

public interface CreditCardInInvoiceService {
  CreditCardInInvoice addCreditCardInInvoice(CreditCardInInvoice creditCardInInvoice);
  CreditCardInInvoice updateCreditCardInInvoice(CreditCardInInvoice creditCardInInvoice);
  boolean deleteCreditCardInInvoice(CreditCardInInvoice creditCardInInvoice);
  CreditCardInInvoice getCreditCardByInvoice(Invoice invoice);
  List<Invoice> getInvoicesByCreditCardNo(String cardNo);
}
