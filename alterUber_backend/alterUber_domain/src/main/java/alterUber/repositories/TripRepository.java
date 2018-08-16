package alterUber.repositories;

import alterUber.dbEntities.Trip;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called tripRepository
// CRUD refers Create, Read, Update, Delete

public interface TripRepository extends CrudRepository<Trip, Long> {

}
