package alterUber.controllers;

import alterUber.dbEntities.Car;
import alterUber.dbEntities.DriverCar;
import alterUber.dbEntities.User;
import alterUber.services.CarService;
import alterUber.services.DriverCarService;
import alterUber.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/driver-car")
@Api(description = "Driver-Car API", tags = {"driver-car"})
public class DriverCarControllers {

  @Autowired
  private DriverCarService driverCarService;

  @Autowired
  private UserService userService;

  @Autowired
  private CarService carService;

  @PostMapping(value = "/addDriverCar")
  public ResponseEntity<DriverCar> addDriverCar(@RequestBody DriverCar driverCar) {
    driverCar = this.driverCarService.addDriverCar(driverCar);
    if (driverCar == null) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(driverCar, HttpStatus.CREATED);
  }


  @PutMapping(value = "/updateDriverCar")
  public ResponseEntity<DriverCar> updateDriverCar(@RequestBody DriverCar driverCar) {
    return new ResponseEntity<>(this.driverCarService.updateDriverCar(driverCar), HttpStatus.OK);
  }

  @PutMapping(value = "/markDriverCarAsDeleted")
  public ResponseEntity<DriverCar> markDriverCarAsDeleted(@RequestBody DriverCar driverCar) {
    return new ResponseEntity<>(this.driverCarService.updateDriverCar(driverCar), HttpStatus.OK);
  }

  @GetMapping(value = "/getCarsOfDriver/{username}")
  public ResponseEntity<List<Car>> getCarsOfDriver(@PathVariable("username") String username) {
    return new ResponseEntity<>(this.driverCarService.getCarsByDriver(username), HttpStatus.OK);
  }

  @GetMapping(value = "/getDriversOfCar/{circulationNo}")
  public ResponseEntity<List<User>> getDriversOfCars(@PathVariable("circulationNo") String circulationNo) {
    return new ResponseEntity<>(this.driverCarService.getDriversOfCar(circulationNo), HttpStatus.OK);
  }

}
