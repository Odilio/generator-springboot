package <%= packageName %>.ports.out;

import <%= packageName %>.adapters.mapper.<%= entityName %>Mapper;
import <%= packageName %>.adapters.entities.<%= entityName %>;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface <%= entityName %>RepositoryPort {

    public Optional<<%= entityName %>> findByUsername(String username);

    public Boolean<<%= entityName %>> existsByEmail(String email);

    public <%= entityName %> registerUser(<%= entityName %>Mapper <%= entityName %>);

}
