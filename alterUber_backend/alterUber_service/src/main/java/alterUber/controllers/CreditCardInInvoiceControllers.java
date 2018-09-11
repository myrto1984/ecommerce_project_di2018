package alterUber.controllers;

import alterUber.dbEntities.CreditCardInInvoice;
import alterUber.dbEntities.Invoice;
import alterUber.services.CreditCardInInvoiceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/credit-card-in-invoice")
@Api(description = "Cradit Card In Invoice", tags = {"credit-card-in-invoice"})
public class CreditCardInInvoiceControllers {

  @Autowired
  private CreditCardInInvoiceService creditCardInInvoiceService;


  @PostMapping(value = "/addCreditCardInInvoice")
  public ResponseEntity<CreditCardInInvoice> addCreditCardInInvoice(@RequestBody CreditCardInInvoice creditCardInInvoice) {
    creditCardInInvoice = this.creditCardInInvoiceService.addCreditCardInInvoice(creditCardInInvoice);
    if (creditCardInInvoice == null) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(creditCardInInvoice, HttpStatus.CREATED);
  }


  @PutMapping(value = "/updateCreditCardInInvoice")
  public ResponseEntity<CreditCardInInvoice> updateCreditCardInInvoice(@RequestBody CreditCardInInvoice creditCardInInvoice) {
    return new ResponseEntity<>(this.creditCardInInvoiceService.updateCreditCardInInvoice(creditCardInInvoice), HttpStatus.OK);
  }


  @GetMapping(value = "/getCreditCardByInvoice")
  public ResponseEntity<CreditCardInInvoice> getCreditCardByInvoice(@RequestBody Invoice invoice) {
    return new ResponseEntity<>(this.creditCardInInvoiceService.getCreditCardByInvoice(invoice), HttpStatus.OK);
  }


  @GetMapping(value = "/getInvoicesByCardNo/{cardNo}")
  public ResponseEntity<List<Invoice>> getInvoicesByCardNo(@PathVariable("cardNo") String cardNo) {
    return new ResponseEntity<>(this.creditCardInInvoiceService.getInvoicesByCreditCardNo(cardNo), HttpStatus.OK);
  }

}
