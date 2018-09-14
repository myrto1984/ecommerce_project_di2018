package controllers;

import dbEntities.Trip;
import dbEntities.User;
import services.TripService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/trip")
@Api(description = "Trip API", tags = {"trip"})
public class TripControllers {

  @Autowired
  private TripService tripService;

  @PostMapping(value = "/createTrip")
  public ResponseEntity<Trip> createTrip (@RequestBody Trip trip) {
    trip = this.tripService.addTrip(trip);
    if ( trip == null ) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(trip, HttpStatus.CREATED);
  }


  @PutMapping(value = "/updateTrip")
  public ResponseEntity<Trip> updateTrip (@RequestBody Trip trip) {
    return new ResponseEntity<>(this.tripService.updateTrip(trip), HttpStatus.OK);
  }


  @GetMapping(value = "/getTripsOfPassenger/{username}")
  public ResponseEntity<List<Trip>> getTripsOfPassenger(@PathVariable("username") String username) {
    return new ResponseEntity<>(this.tripService.getTripsOfPassenger(username), HttpStatus.OK);
  }


  @GetMapping(value = "/getTripsOfDriver/{username}")
  public ResponseEntity<List<Trip>> getTripsOfDriver(@PathVariable("username") String username) {
    return new ResponseEntity<>(this.tripService.getTripsOfDriver(username), HttpStatus.OK);
  }


  @GetMapping(value = "/getTripsOfCar/{circulationNo}")
  public ResponseEntity<List<Trip>> getTripsOfCar(@PathVariable("circulationNo") String circulationNo) {
    return new ResponseEntity<>(this.tripService.getTripsOfCar(circulationNo), HttpStatus.OK);
  }

  @GetMapping(value = "/getAvgRatingOfPassengerForDrivers/{passengerUsername}")
  public ResponseEntity<Double> getAvgRatingOfPassengerForDrivers(@PathVariable("passengerUsername") String passenger) {
    return new ResponseEntity<>(this.tripService.getAverageRatingOfPassengerForDrivers(passenger), HttpStatus.OK);
  }

  @GetMapping(value = "/getAvgRatingForPassenger/{passengerUsername}")
  public ResponseEntity<Double> getAvgRatingForPassenger(@PathVariable("passengerUsername") String passenger) {
    return new ResponseEntity<>(this.tripService.getAverageRatingForPassenger(passenger), HttpStatus.OK);
  }

  @GetMapping(value = "/getAvgRatingForDriver/{driverUsername}")
  public ResponseEntity<Double> getAvgRatingForDriver(@PathVariable("driverUsername") String driver) {
    return new ResponseEntity<>(this.tripService.getAverageRatingForDriver(driver), HttpStatus.OK);
  }

  @GetMapping(value = "/getAvgRatingForCar/{carLicencePlate}")
  public ResponseEntity<Double> getAvgRatingForCar(@PathVariable("carLicencePlate") String car) {
    return new ResponseEntity<>(this.tripService.getAverageRatingForCar(car), HttpStatus.OK);
  }

}
