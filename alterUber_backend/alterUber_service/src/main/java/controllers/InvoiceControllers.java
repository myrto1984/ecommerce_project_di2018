package controllers;

import dbEntities.Invoice;
import services.InvoiceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/invoice")
@Api(description = "Invoice API", tags = {"invoice"})
public class InvoiceControllers {

  @Autowired
  private InvoiceService invoiceService;


  @PostMapping(value = "/createInvoice")
  public ResponseEntity<Invoice> createInvoice (@RequestBody Invoice invoice) {
    invoice = this.invoiceService.addInvoice(invoice);
    if (invoice == null) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(invoice, HttpStatus.CREATED);
  }


  @PutMapping(value = "/updateInvoice")
  public ResponseEntity<Invoice> updateInvoice (@RequestBody Invoice invoice) {
    return new ResponseEntity<>(this.invoiceService.updateInvoice(invoice), HttpStatus.OK);
  }

  @GetMapping(value = "/getInvoiceByTrip/{tripId}")
  public ResponseEntity<Invoice> getInvoiceByTrip (@PathVariable("tripId") long tripId) {
    return new ResponseEntity<>(this.invoiceService.getInvoiceByTripId(tripId), HttpStatus.OK);
  }

  @GetMapping(value = "/getInvoicesByPaymentType/{paidInCash}")
  public ResponseEntity<List<Invoice>> getInvoicesByPaymentType (@PathVariable("paidInCash") int paidInCash) {
    return new ResponseEntity<>(this.invoiceService.getInvoicesByPaymentMethod(paidInCash), HttpStatus.OK);
  }

}
