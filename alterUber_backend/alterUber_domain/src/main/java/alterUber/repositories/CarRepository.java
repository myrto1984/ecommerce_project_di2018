package alterUber.repositories;

import alterUber.dbEntities.Car;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called carRepository
// CRUD refers Create, Read, Update, Delete

public interface CarRepository extends CrudRepository<Car, Long> {

}
