package services;

import dbEntities.Invoice;
import dbEntities.Trip;

import java.util.List;

public interface InvoiceService {
  Invoice addInvoice(Invoice invoice);
  Invoice updateInvoice(Invoice invoice);
  boolean deleteInvoice(Invoice invoice);
  Invoice getInvoiceByTripId(long tripId);
  List<Invoice> getInvoicesByPaymentMethod(int paidInCash);
}
