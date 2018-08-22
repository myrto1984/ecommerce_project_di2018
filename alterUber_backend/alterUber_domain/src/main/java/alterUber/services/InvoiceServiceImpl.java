package alterUber.services;

import alterUber.dbEntities.Invoice;
import alterUber.dbEntities.Trip;
import alterUber.repositories.InvoiceRepository;
import alterUber.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

  @Autowired
  private InvoiceRepository invoiceRepository;

  @Autowired
  private TripRepository tripRepository;


  @Override
  public synchronized Invoice addInvoice(Invoice invoice) {
    if (this.invoiceRepository.fetchCountExistingInvoiceByTrip(invoice.getTrip()) > 0) {
      return null;
    }
    return this.invoiceRepository.save(invoice);
  }


  @Override
  public Invoice updateInvoice(Invoice invoice) {
    return this.invoiceRepository.save(invoice);
  }


  @Override
  public boolean deleteInvoice(Invoice invoice) {
    this.invoiceRepository.delete(invoice);
    return (this.invoiceRepository.fetchCountExistingById(invoice.getInvoiceId()) == 0);
  }


  @Override
  public Invoice getInvoiceByTripId(long tripId) {
    Trip trip = this.tripRepository.fetchById(tripId);
    return this.invoiceRepository.findByTrip(trip);
  }


  @Override
  public List<Invoice> getInvoicesByPaymentMethod(int paidInCash) {
    return this.invoiceRepository.findAllByPaidInCash(paidInCash);
  }


}
