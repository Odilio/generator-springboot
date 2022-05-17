package <%= packageName %>.adapters.outbound;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import <%= packageName %>.adapters.entities.ERole;
import <%= packageName %>.adapters.entities.Role;

@Repository
public class RoleRepositoryAdapter implements JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
