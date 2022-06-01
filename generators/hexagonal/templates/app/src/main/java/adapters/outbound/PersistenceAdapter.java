package <%= packageName %>.adapters.outbound;

import <%= packageName %>.adapters.mapper.<%= entityName %>Mapper;
import <%= packageName %>.adapters.mapper.Converter;
import <%= packageName %>.adapters.outbound.repositories.<%= entityName %>Repository;
import <%= packageName %>.adapters.entities.<%= entityName %>;
import <%= packageName %>.ports.out.<%= entityName %>PersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
class <%= entityName %>PersistenceAdapter implements <%= entityName %>PersistencePort {

    @Autowired
    private <%= entityName %>Repository boilerplateRepository;

     public Optional<<%= entityName %>> findById(Long codigo) {
        return boilerplateRepository.findById(codigo);
    }

     public Page<<%= entityName %>> findAll(Pageable page) {
        return boilerplateRepository.findAll( page);
    }


     public <%= entityName %> save(<%= entityName %>Mapper boilerplate) {
        <%= entityName %> boilerplateModel = (<%= entityName %>)Converter.toModel(boilerplate, <%= entityName %>.class);
        return boilerplateRepository.save(boilerplateModel);
    }

     public void deleteById(Long id ) {
        boilerplateRepository.deleteById(id);
    }
}
