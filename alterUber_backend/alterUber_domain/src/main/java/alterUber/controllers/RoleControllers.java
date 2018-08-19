package alterUber.controllers;

import alterUber.dbEntities.Role;
import alterUber.services.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/role")
@Api(description = "Role API",  tags = {"role"})
public class RoleControllers {

  @Autowired
  private RoleService roleService;

  @GetMapping("/getAllRoles")
  public ResponseEntity<List<Role>> getAllRoles() {
    return new ResponseEntity<>(this.roleService.getAllRoles(), HttpStatus.OK);
  }


}