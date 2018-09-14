package repositories;

import dbEntities.Car;
import dbEntities.DriverCar;
import dbEntities.Trip;
import dbEntities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called tripRepository
// CRUD refers Create, Read, Update, Delete

public interface TripRepository extends CrudRepository<Trip, Long> {

  List<Trip> findAllByDriverCar(DriverCar driverCar);

  List<Trip> findAllByPassenger(User passenger);

  @Query("SELECT t FROM Trip t WHERE t.id = :tripId")
  Trip fetchById(@Param("tripId") long tripId);

  @Query(value = "SELECT COALESCE(AVG(t.passengerRatingOfDriver), 0.0) FROM trip t WHERE t.passenger = :passenger",
         nativeQuery = true)
  double fetchAvgRatingOfPassengerForDrivers(@Param("passenger") String passenger);

  @Query(value = "SELECT COALESCE(AVG(t.driverRatingOfPassenger), 0.0) FROM trip t WHERE t.passenger = :passenger",
         nativeQuery = true)
  double fetchAvgRatingForPassenger(@Param("passenger") String passenger);

  @Query(value = "SELECT COALESCE(AVG(t.passengerRatingOfDriver),0.0) FROM trip t, driver_car dc WHERE t.driverCar = dc.id AND dc.driver = :driver",
          nativeQuery = true)
  double fetchAvgRatingForDriver(@Param("driver") String driver);

  @Query(value = "SELECT COALESCE(AVG(t.passengerRatingOfCar),0.0) FROM trip t, driver_car dc WHERE t.driverCar = dc.id AND dc.carCircNo = :car",
         nativeQuery = true)
  double fetchAvgRatingForCar(@Param("car") String car);

}
