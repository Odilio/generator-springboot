package <%= packageName %>.ports.out;

import <%= packageName %>.adapters.mapper.<%= entityName %>Mapper;
import <%= packageName %>.adapters.dto.<%= entityName %>DTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface <%= entityName %>IntegrationPort {

    public Optional<<%= entityName %>DTO> findById(Long codigo);

    public Page<<%= entityName %>DTO> findAll(Pageable page);

    public <%= entityName %>DTO findById(String)save(<%= entityName %>Mapper <%= entityName %>);

    public void deleteById(Long id);
}
