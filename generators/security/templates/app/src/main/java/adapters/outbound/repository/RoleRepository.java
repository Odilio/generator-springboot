package <%= packageName %>.adapters.outbound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import <%= packageName %>.adapters.entities.ERole;
import <%= packageName %>.adapters.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
