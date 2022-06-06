package <%= packageName %>.adapters.outbound;

import java.util.Optional;
import <%= packageName %>.adapters.outbound.repository.<%= entityName %>Repositories;
import <%= packageName %>.ports.out.<%= entityName %>RepositoryPort;
import <%= packageName %>.adapters.entities.<%= entityName %>;
import org.springframework.beans.factory.annotation.Autowired;

public class <%= entityName %>RepositoryAdapter implements <%= entityName %>RepositoryPort {

  @Autowired
  private <%= entityName %>Repository userRepository;

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
