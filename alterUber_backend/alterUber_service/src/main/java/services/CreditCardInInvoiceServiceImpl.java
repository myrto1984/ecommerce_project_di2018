package services;

import dbEntities.CreditCardInInvoice;
import dbEntities.Invoice;
import repositories.CreditCardInInvoiceRepository;
import repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditCardInInvoiceServiceImpl implements CreditCardInInvoiceService {

  @Autowired
  private CreditCardInInvoiceRepository creditCardInInvoiceRepository;

  @Autowired
  private InvoiceRepository invoiceRepository;


  @Override
  public synchronized CreditCardInInvoice addCreditCardInInvoice(CreditCardInInvoice creditCardInInvoice) {
    if ( this.creditCardInInvoiceRepository.fetchCountExistingInvoice(creditCardInInvoice.getInvoice()) > 0 ) {
      return null;
    }
    return this.creditCardInInvoiceRepository.save(creditCardInInvoice);
  }


  @Override
  public CreditCardInInvoice updateCreditCardInInvoice(CreditCardInInvoice creditCardInInvoice) {
    return this.creditCardInInvoiceRepository.save(creditCardInInvoice);
  }


  @Override
  public boolean deleteCreditCardInInvoice(CreditCardInInvoice creditCardInInvoice) {
    this.creditCardInInvoiceRepository.delete(creditCardInInvoice);
    return !this.creditCardInInvoiceRepository.findById(creditCardInInvoice.getId()).isPresent();
  }


  @Override
  public CreditCardInInvoice getCreditCardByInvoice(Invoice invoice) {
    return this.creditCardInInvoiceRepository.findByInvoice(invoice);
  }


  @Override
  public List<Invoice> getInvoicesByCreditCardNo(String cardNo) {
    List<Invoice> invoices = new ArrayList<>();
    this.creditCardInInvoiceRepository.findByCardNo(cardNo).forEach(
            cci -> invoices.add(cci.getInvoice())
    );
    return invoices;
  }


}
