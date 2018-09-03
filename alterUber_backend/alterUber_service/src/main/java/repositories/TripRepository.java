package repositories;

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
}
