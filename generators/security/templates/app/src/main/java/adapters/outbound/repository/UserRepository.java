package <%= packageName %>.adapters.outbound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import <%= packageName %>.adapters.entities.<%= entityName %>;

@Repository
public interface UserRepository extends JpaRepository<<%= entityName %>, Long> {
  Optional<<%= entityName %>> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
