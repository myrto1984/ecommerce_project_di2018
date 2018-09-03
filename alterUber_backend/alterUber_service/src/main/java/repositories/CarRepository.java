package repositories;

import dbEntities.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called carRepository
// CRUD refers Create, Read, Update, Delete

public interface CarRepository extends CrudRepository<Car, Long> {

  @Query("SELECT count(c) FROM Car c WHERE c.circulationNo = :circulationNo")
  int fetchExistingCirculationNo(@Param("circulationNo") String circulationNo);

  Car findByCirculationNo(String circulationNo);

}
