package controllers;

import dbEntities.UserCreditCard;
import services.UserCreditCardService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/user-credit-card")
@Api(description = "User Credit Card API", tags = {"user-credit-card"})
public class UserCreditCardControllers {

  @Autowired
  private UserCreditCardService userCreditCardService;


  @PostMapping(value = "/addUserCreditCard")
  public ResponseEntity<UserCreditCard> addUserCreditCard(@RequestBody UserCreditCard userCreditCard) {
    userCreditCard = this.userCreditCardService.addUserCreditCard(userCreditCard);
    if (userCreditCard == null) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(userCreditCard, HttpStatus.CREATED);
  }


  @PutMapping(value = "/updateUserCreditCard")
  public ResponseEntity<UserCreditCard> updateUserCreditCard(@RequestBody UserCreditCard userCreditCard) {
    return new ResponseEntity<>(this.userCreditCardService.updateUserCreditCard(userCreditCard), HttpStatus.OK);
  }


  @DeleteMapping(value = "/deleteUserCreditCard")
  public ResponseEntity<Boolean> deleteUserCreditCard(@RequestBody UserCreditCard userCreditCard) {
    return new ResponseEntity<>(this.userCreditCardService.deleteUserCreditCard(userCreditCard), HttpStatus.OK);
  }


  @GetMapping("/getAllUserCreditCards/{username}")
  public ResponseEntity<List<UserCreditCard>> getAllUserCreditCard(@PathVariable("username") String username) {
    return new ResponseEntity<>(this.userCreditCardService.getCreditCardsOfUser(username), HttpStatus.OK);
  }

}
