package <%= packageName %>.application.services;

import <%= packageName %>.ports.in.<%= entityName %>ServicePort;
import <%= packageName %>.ports.out.<%= entityName %>PersistencePort;
import <%= packageName %>.adapters.mapper.<%= entityName %>Mapper;
import <%= packageName %>.adapters.mapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class <%= entityName %>Service implements <%= entityName %>ServicePort{

    private final <%= entityName %>PersistencePort <%= entityVarName %>Repository;

    @Autowired
    public <%= entityName %>Service(<%= entityName %>PersistencePort <%= entityVarName %>Repository) {
        this.<%= entityVarName %>Repository = <%= entityVarName %>Repository;
    }

    public Page<<%= entityName %>Mapper> listarTodos(Pageable page) {
        return new PageImpl(Converter.toCollection(<%= entityVarName %>Repository.findAll(page).getContent(), <%= entityName %>Mapper.class));
    }

    public <%= entityName %>Mapper buscarPorCodigo(Long id) {
        return (<%= entityName %>Mapper)Converter.toModel(<%= entityVarName %>Repository.findById(id).get(), <%= entityName %>Mapper.class);
    }

    public <%= entityName %>Mapper salvar<%= entityName %>(<%= entityName %>Mapper <%= entityVarName %>) {
        return (<%= entityName %>Mapper)Converter.toModel(<%= entityVarName %>Repository.save(<%= entityVarName %>), <%= entityName %>Mapper.class );
    }

    public void remover<%= entityName %>(Long id) {
        <%= entityVarName %>Repository.deleteById(id);
    }
}
