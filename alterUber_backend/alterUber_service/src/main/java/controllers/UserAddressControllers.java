package controllers;

import dbEntities.UserAddress;
import services.UserAddressService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/user-address")
@Api(description = "User Address API", tags = {"user-address"})
public class UserAddressControllers {

  @Autowired
  private UserAddressService userAddressService;


  @PostMapping(value = "/addUserAddress")
  public ResponseEntity<UserAddress> addUserAddress(@RequestBody UserAddress userAddress) {
    userAddress = this.userAddressService.addUserAddress(userAddress);
    if (userAddress == null) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(userAddress, HttpStatus.CREATED);
  }


  @PutMapping(value = "/updateUserAddress")
  public ResponseEntity<UserAddress> updateUserAddress(@RequestBody UserAddress userAddress) {
    return new ResponseEntity<>(this.userAddressService.updateUserAddress(userAddress), HttpStatus.OK);
  }


  @DeleteMapping(value = "/deleteUserAddress")
  public ResponseEntity<Boolean> deleteUserAddress(@RequestBody UserAddress userAddress) {
    return new ResponseEntity<>(this.userAddressService.deleteUserAddress(userAddress), HttpStatus.OK);
  }


  @GetMapping("/getAllUserAddresses/{username}")
  public ResponseEntity<List<UserAddress>> getAllUserAddresses(@PathVariable("username") String username) {
    return new ResponseEntity<>(this.userAddressService.getAddressesOfUser(username), HttpStatus.OK);
  }


}
