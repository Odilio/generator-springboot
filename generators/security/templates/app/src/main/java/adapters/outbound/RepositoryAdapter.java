package <%= packageName %>.adapters.outbound;

import java.util.Optional;
import <%= packageName %>.adapters.outbound.repositories.<%= entityName %>Repositories;
import <%= packageName %>.ports.out.<%= entityName %>RepositoryPort;
import <%= packageName %>.adapters.entities.<%= entityName %>;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class <%= entityName %>RepositoryAdapter implements <%= entityName %>RepositoryPort {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private <%= entityName %>Repository userRepository;

  public Optional<<%= entityName %>> findByUsername(String username){
    return null;
  };

  public <%= entityName %> registerUser(<%= entityName %> <%= entityVarName %>){
    <%= entityVarName %>.setPassword(passwordEncoder.encode(<%= entityVarName %>.getPassword()));
    return userRepository.save();
  };

  public Boolean existsByEmail(String email){
    return null;
  };
}