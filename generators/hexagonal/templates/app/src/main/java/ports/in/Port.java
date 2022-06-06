package <%= packageName %>.ports.in;

import <%= packageName %>.adapters.mapper.<%= entityName %>Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface <%= entityName %>ServicePort {

    public <%= entityName %>Mapper find(Long codigo );

    public Page<<%= entityName %>Mapper> findAll(Pageable page );

    public <%= entityName %>Mapper save<%= entityName %>(<%= entityName %>Mapper entity);

    public void delete<%= entityName %>(Long id );
}

