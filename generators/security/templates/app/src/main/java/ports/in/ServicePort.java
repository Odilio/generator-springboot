package <%= packageName %>.ports.in;

import java.util.Optional;

import <%= packageName %>.adapters.entities.<%= entityName %>;

public interface <%= entityName %>ServicePort {
  Optional<<%= entityName %>> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
