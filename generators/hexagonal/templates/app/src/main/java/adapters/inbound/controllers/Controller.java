package <%= packageName %>.adapters.inbound.controllers;

import <%= packageName %>.adapters.dto.<%= entityName %>DTO;
import <%= packageName %>.adapters.entities.<%= entityName %>;
import <%= packageName %>.application.services.<%= entityName %>Service;
import <%= packageName %>.adapters.mapper.<%= entityName %>Mapper;
import <%= packageName %>.adapters.mapper.Converter;
import <%= packageName %>.ports.in.<%= entityName %>ServicePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("<%= basePath %>")
@Slf4j
public class <%= entityName %>Controller {

    private final <%= entityName %>ServicePort <%= entityVarName %>Service;

    @Autowired
    public <%= entityName %>Controller(<%= entityName %>ServicePort <%= entityVarName %>Service) {
        this.<%= entityVarName %>Service = <%= entityVarName %>Service;
    }

    @GetMapping("/v1/")
    public Page<<%= entityName %>DTO> getAll<%= entityName %>s(Pageable page) {
        return new PageImpl(Converter.toCollection(<%= entityVarName %>Service.listarTodos(page).getContent(), <%= entityName %>DTO.class)) ;
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<<%= entityName %>DTO> get<%= entityName %>ById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body((<%= entityName %>DTO)Converter.toModel(<%= entityVarName %>Service
                .buscarPorCodigo(id), <%= entityName %>DTO.class));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create<%= entityName %>(@RequestBody @Validated <%= entityName %> <%= entityVarName %>DTO) {
        <%= entityName %>Mapper <%= entityVarName %> = (<%= entityName %>Mapper) Converter.toModel(<%= entityVarName %>DTO, <%= entityName %>Mapper.class);
        <%= entityVarName %>Service.salvar<%= entityName %>(<%= entityVarName %>);
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<HttpStatus> delete<%= entityName %>(@PathVariable Long id) {
        <%= entityVarName %>Service.remover<%= entityName %>(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
