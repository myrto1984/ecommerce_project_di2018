package repositories;

import dbEntities.Car;
import dbEntities.DriverCar;
import dbEntities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called driverCarRepository
// CRUD refers Create, Read, Update, Delete

public interface DriverCarRepository extends CrudRepository<DriverCar, Long> {

  List<DriverCar> findAllByDriver(User driver);

  List<DriverCar> findAllByCar(Car car);

  @Query("SELECT count(dc) FROM DriverCar dc WHERE dc.driver = :driver AND dc.car = :car")
  int fetchCountExistingDriverAndCar(@Param("driver") User driver, @Param("car") Car car);
}
