package services;

import dbEntities.Car;
import dbEntities.DriverCar;
import dbEntities.Trip;
import dbEntities.User;
import repositories.CarRepository;
import repositories.DriverCarRepository;
import repositories.TripRepository;
import repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {

  @Autowired
  private TripRepository tripRepository;

  @Autowired
  private DriverCarRepository driverCarRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CarRepository carRepository;


  @Override
  public synchronized Trip addTrip (Trip trip) {
    return this.tripRepository.save(trip);
  }


  @Override
  public Trip updateTrip (Trip trip) {
    return this.tripRepository.save(trip);
  }


  @Override
  public boolean deleteTrip (Trip trip) {
    this.tripRepository.delete(trip);
    return !this.tripRepository.findById(trip.getId()).isPresent();
  }


  @Override
  public List<Trip> getTripsOfPassenger (String username) {
    User passenger = this.userRepository.findByUsername(username);
    return this.tripRepository.findAllByPassenger(passenger);
  }


  @Override
  public List<Trip> getTripsOfDriver (String username) {
    User driver = this.userRepository.findByUsername(username);
    List<DriverCar> driverCars = this.driverCarRepository.findAllByDriver(driver);
    List<Trip> trips = new ArrayList<>();
    driverCars.forEach( dc -> trips.addAll(this.tripRepository.findAllByDriverCar(dc)) );

    return trips;
  }


  @Override
  public List<Trip> getTripsOfCar (String circulationNo) {
    Car car = this.carRepository.findByCirculationNo(circulationNo);
    List<DriverCar> driverCars = this.driverCarRepository.findAllByCar(car);
    List<Trip> trips = new ArrayList<>();
    driverCars.forEach( dc -> trips.addAll(this.tripRepository.findAllByDriverCar(dc)) );
    return trips;
  }

  @Override
  public double getAverageRatingOfPassengerForDrivers (String passenger) {
    return this.tripRepository.fetchAvgRatingOfPassengerForDrivers(passenger);
  }

  @Override
  public double getAverageRatingForPassenger (String passenger) {
    return this.tripRepository.fetchAvgRatingForPassenger(passenger);
  }

  @Override
  public double getAverageRatingForDriver (String driver) {
    return this.tripRepository.fetchAvgRatingForDriver(driver);
  }

  @Override
  public double getAverageRatingForCar (String car) {
    return this.tripRepository.fetchAvgRatingForCar(car);
  }

}
