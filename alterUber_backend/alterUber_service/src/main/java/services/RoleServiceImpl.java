package services;

import dbEntities.Role;
import repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public List<Role> getAllRoles() {
    List<Role> roles = new ArrayList<>();
    this.roleRepository.findAll().forEach(r -> roles.add(r));
    return roles;
  }
}
