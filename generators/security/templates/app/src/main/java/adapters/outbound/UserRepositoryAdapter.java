package <%= packageName %>.adapters.outbound;

import java.util.Optional;
import <%= packageName %>.ports.out.<%= entityName %>RepositoryPort;
import <%= packageName %>.adapters.entities.<%= entityName %>;

public class <%= entityName %>RepositoryAdapter implements <%= entityName %>RepositoryPort {
  public Optional<<%= entityName %>> findByUsername(String username){
    return null;
  };

  public Boolean existsByUsername(String username){
    return null;
  };

  public Boolean existsByEmail(String email){
    return null;
  };
}
