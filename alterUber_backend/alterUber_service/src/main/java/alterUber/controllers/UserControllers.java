package alterUber.controllers;

import alterUber.dbEntities.Role;
import alterUber.dbEntities.User;
import alterUber.dbEntities.UserAddress;
import alterUber.services.RoleService;
import alterUber.services.UserAddressService;
import alterUber.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
@Api(description = "User API",  tags = {"user"})
public class UserControllers {

  @Autowired
  private UserService userService;

  @GetMapping("/getIfUsernameExists/{username}")
  public ResponseEntity<Boolean> getIfUsernameExists(@PathVariable("username") String username) {
    return new ResponseEntity<>(this.userService.getIfUsernameExists(username), HttpStatus.OK);
  }

  @GetMapping("/login/{username}/{password}")
  public ResponseEntity<User> login(@PathVariable("username") String username, @PathVariable("password") String password) {
    // TODO: create proper login method
    User user = this.userService.getUser(username, password);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping("/logout")
  public ResponseEntity<Boolean> logout() {
    // TODO: create proper logout method
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  @GetMapping("/getAllUsers")
  public ResponseEntity<List<User>> getAllUsers() {
    return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody User user) {
    user = this.userService.addUser(user);
    if (user == null) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  @PutMapping("/markAccountAsDeleted")
  public ResponseEntity<User> markAccountAsDeleted(@RequestBody User user) {
    user.setDeletedAccount(1);
    return new ResponseEntity<>(this.userService.updateUser(user), HttpStatus.OK);
  }

  @PutMapping("/update")
  public ResponseEntity<User> update(@RequestBody User user) {
    return new ResponseEntity<>(this.userService.updateUser(user), HttpStatus.OK);
  }


}
