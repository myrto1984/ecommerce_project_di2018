package alterUber.repositories;

import alterUber.dbEntities.DriverCar;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called driverCarRepository
// CRUD refers Create, Read, Update, Delete

public interface DriverCarRepository extends CrudRepository<DriverCar, Long> {
}
