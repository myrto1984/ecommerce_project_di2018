package repositories;

import dbEntities.Role;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called RoleRepository
// CRUD refers Create, Read, Update, Delete


public interface RoleRepository extends CrudRepository<Role, Long> {

  Role findByRole(String role);

}
