package controllers;

import dbEntities.Car;
import services.CarService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/car")
@Api(description = "Car API", tags = {"car"})
public class CarControllers {

  @Autowired
  private CarService carService;


  @PostMapping("/addNewCar")
  public ResponseEntity<Car> addNewCar(@RequestBody Car car) {
    car = this.carService.addCar(car);
    if ( car == null ) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(car, HttpStatus.CREATED);
  }

  @PutMapping("/updateCar")
  public ResponseEntity<Car> updateCar(@RequestBody Car car) {
    return new ResponseEntity<>(this.carService.updateCar(car), HttpStatus.OK);
  }

  @PutMapping("/markCarAsDeleted")
  public ResponseEntity<Car> markCarAsDeleted(@RequestBody Car car) {
    car.setDeleted(1);
    return new ResponseEntity<>(this.carService.updateCar(car), HttpStatus.OK);
  }

  @GetMapping("/getCarByCircNo/{circulationNo}")
  public ResponseEntity<Car> getCarByCircNo(@PathVariable("circulationNo") String circulationNo) {
    return new ResponseEntity<>(this.carService.getCar(circulationNo), HttpStatus.OK);
  }

}
