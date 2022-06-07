package <%= packageName %>.ports.out;

import <%= packageName %>.adapters.mapper.<%= entityName %>Mapper;
import <%= packageName %>.adapters.entities.<%= entityName %>;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface <%= entityName %>PersistencePort {

    public Optional<<%= entityName %>> findById(Long codigo);

    public Page<<%= entityName %>> findAll(Pageable page);

    public <%= entityName %> save(<%= entityName %>Mapper <%= entityName %>);

    public void deleteById(Long id);
}
