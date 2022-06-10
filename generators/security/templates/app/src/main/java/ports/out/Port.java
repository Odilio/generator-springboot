package <%= packageName %>.ports.out;

import <%= packageName %>.adapters.mapper.<%= entityName %>Mapper;
import <%= packageName %>.adapters.entities.<%= entityName %>;

import java.util.Optional;

public interface <%= entityName %>RepositoryPort {

    public Optional<<%= entityName %>> findByUsername(String username);

    public Boolean existsByEmail(String email);

    public <%= entityName %> registerUser(<%= entityName %>Mapper <%= entityName %>);

}
