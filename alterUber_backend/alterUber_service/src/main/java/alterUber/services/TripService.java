package alterUber.services;

import alterUber.dbEntities.Trip;

import java.util.List;

public interface TripService {
  Trip addTrip (Trip trip);
  Trip updateTrip (Trip trip);
  boolean deleteTrip (Trip trip);
  List<Trip> getTripsOfPassenger (String username);
  List<Trip> getTripsOfDriver (String username);
  List<Trip> getTripsOfCar (String circulationNo);

}
