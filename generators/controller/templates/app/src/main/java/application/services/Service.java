package <%= packageName %>.application.services;

import <%= packageName %>.adapters.entities.<%= entityName %>;
import <%= packageName %>.ports.out.<%= entityName %>PersistencePort;
import <%= packageName %>.adapters.mapper.<%= entityName %>Mapper;
import <%= packageName %>.adapters.mapper.Converter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class <%= entityName %>Service {

    private final <%= entityName %>PersistencePort <%= entityVarName %>Repository;

    @Autowired
    public <%= entityName %>Service(<%= entityName %>PersistencePort <%= entityVarName %>Repository) {
        this.<%= entityVarName %>Repository = <%= entityVarName %>Repository;
    }

    public Page<<%= entityName %>> findAll<%= entityName %>s(Pageable page) {
        return <%= entityVarName %>Repository.findAll(page);
    }

    public Optional<<%= entityName %>> find<%= entityName %>ById(Long id) {
        return <%= entityVarName %>Repository.findById(id);
    }

    public <%= entityName %>Mapper save<%= entityName %>(<%= entityName %>Mapper <%= entityVarName %>) {
        return (<%= entityName %>Mapper)Converter.toModel(<%= entityVarName %>Repository.save(<%= entityVarName %>), <%= entityName %>Mapper.class );
    }

    public void delete<%= entityName %>ById(Long id) {
        <%= entityVarName %>Repository.deleteById(id);
    }
}
