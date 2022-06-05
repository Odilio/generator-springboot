package <%= packageName %>.application.services;

import java.util.Optional;
import <%= packageName %>.port.in.<%= entityName %>ServicePort;
import <%= packageName %>.adapters.entities.<%= entityName %>;

@Component
public class <%= entityName %>Service implements <%= entityName %>ServicePort {

  @Autowired
  private lateinit var repository: <%= entityName %>RepositoryPort

  Optional<<%= entityName %>> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
