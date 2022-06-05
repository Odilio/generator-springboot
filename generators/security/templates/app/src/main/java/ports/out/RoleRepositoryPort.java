package <%= packageName %>.ports.out;

import java.util.Optional;

import <%= packageName %>.adapters.entities.ERole;
import <%= packageName %>.adapters.entities.Role;


public interface RoleRepositoryPort {
  Optional<Role> findByName(ERole name);
}
