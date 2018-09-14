package services;

import dbEntities.Car;
import dbEntities.Trip;
import dbEntities.User;

import java.util.List;

public interface TripService {
  Trip addTrip (Trip trip);
  Trip updateTrip (Trip trip);
  boolean deleteTrip (Trip trip);
  List<Trip> getTripsOfPassenger (String username);
  List<Trip> getTripsOfDriver (String username);
  List<Trip> getTripsOfCar (String circulationNo);
  double getAverageRatingOfPassengerForDrivers (String passenger);
  double getAverageRatingForPassenger (String passenger);
  double getAverageRatingForDriver (String driver);
  double getAverageRatingForCar (String car);

}
