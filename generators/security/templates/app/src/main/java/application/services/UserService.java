package <%= packageName %>.application.services;

import java.util.Optional;
import <%= packageName %>.ports.in.<%= entityName %>ServicePort;
import <%= packageName %>.adapters.entities.<%= entityName %>;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class <%= entityName %>Service implements <%= entityName %>ServicePort {

  @Autowired
  private <%= entityName %>RepositoryPort userRepository;

  public Optional<<%= entityName %>> findByUsername(String username){
    return null;
  }

  public Boolean existsByUsername(String username){
    return null;
  }

  public Boolean existsByEmail(String email){
    return null;
  }
}
