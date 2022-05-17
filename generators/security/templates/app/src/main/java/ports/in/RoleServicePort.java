package <%= packageName %>.ports.in;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import <%= packageName %>.adapters.entities.ERole;
import <%= packageName %>.adapters.entities.Role;


public interface RoleServicePort {
  Optional<Role> findByName(ERole name);
}
