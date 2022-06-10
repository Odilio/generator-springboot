package <%= packageName %>.adapters.inbound.controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import <%= packageName %>.adapters.mapper.Converter;
import <%= packageName %>.adapters.mapper.<%= entityName %>Mapper;
import <%= packageName %>.adapters.entities.Auth;
import <%= packageName %>.ports.out.AuthRepositoryPort;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private <%= entityName %>RepositoryPort userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody <%= entityName %> userModel, final HttpServletRequest request) {
        <%= entityName %> user = userService.registerUser((<%= entityName %>Mapper) Converter.toModel(userModel,<%= entityName %>Mapper.class));
       
        return ResponseEntity.ok().body("Success");
    }

}
