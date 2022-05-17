package <%= packageName %>.adapters.outbound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import <%= packageName %>.adapters.entities.User;

@Repository
public class UserRepositoryAdapter implements JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
