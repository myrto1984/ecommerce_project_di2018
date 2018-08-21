package alterUber.controllers;

import alterUber.dbEntities.UserCreditCard;
import alterUber.services.UserCreditCardService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/user-credit-card")
@Api(description = "User Credit Card API", tags = "user-credit-card")
public class UserCreditCardControllers {

  @Autowired
  private UserCreditCardService userCreditCardService;


  @PostMapping(value = "/addUserCreditCard")
  public ResponseEntity<UserCreditCard> addUserCreditCard(@RequestBody UserCreditCard userCreditCard) {
    return new ResponseEntity<>(this.userCreditCardService.addUserCreditCard(userCreditCard), HttpStatus.CREATED);
  }


  @PutMapping(value = "/updateUserCreditCard")
  public ResponseEntity<UserCreditCard> updateUserCreditCard(@RequestBody UserCreditCard userCreditCard) {
    return new ResponseEntity<>(this.userCreditCardService.updateUserCreditCard(userCreditCard), HttpStatus.OK);
  }


  @DeleteMapping(value = "/deleteUserCreditCard")
  public boolean deleteUserCreditCard(@RequestBody UserCreditCard userCreditCard) {
    return this.userCreditCardService.deleteUserCreditCard(userCreditCard);
  }


  @GetMapping("/getAllUserCreditCards/{username}")
  public ResponseEntity<List<UserCreditCard>> getAllUserCreditCard(@PathVariable("username") String username) {
    return new ResponseEntity<>(this.userCreditCardService.getCreditCardsOfUser(username), HttpStatus.OK);
  }

}
