package <%= packageName %>.ports.out;

import java.util.Optional;


import <%= packageName %>.adapters.entities.<%= entityName %>;

public interface <%= entityName %>RepositoryPort {
  Optional<<%= entityName %>> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
