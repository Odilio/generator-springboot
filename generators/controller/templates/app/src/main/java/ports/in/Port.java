package <%= packageName %>.ports.in;

import <%= packageName %>.adapters.mapper.<%= entityName %>Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface <%= entityName %>ServicePort {

    public <%= entityName %>Mapper buscarPorCodigo(Long codigo );

    public Page<<%= entityName %>Mapper> listarTodos(Pageable page );

    public <%= entityName %>Mapper salvar<%= entityName %>(<%= entityName %>Mapper entity);

    public void remover<%= entityName %>(Long id );
}

