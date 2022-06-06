package <%= packageName %>.adapters.inbound.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import <%= packageName %>.adapters.entities.<%= entityName %>;
import <%= packageName %>.adapters.mapper.<%= entityName %>Mapper;
import <%= packageName %>.application.services.<%= entityName %>Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.jackson.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

@WebMvcTest(controllers = <%= entityName %>Controller.class)
@ActiveProfiles("test")
class <%= entityName %>ControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private <%= entityName %>Service <%= entityVarName %>Service;

    @Autowired private ObjectMapper objectMapper;

    private List<<%= entityName %>Mapper> <%= entityVarName %>List;

    @BeforeEach
    void setUp() {
        this.<%= entityVarName %>List = new ArrayList<>();
        this.<%= entityVarName %>List.add(new <%= entityName %>Mapper(1L, "text 1"));
        this.<%= entityVarName %>List.add(new <%= entityName %>Mapper(2L, "text 2"));
        this.<%= entityVarName %>List.add(new <%= entityName %>Mapper(3L, "text 3"));

        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }

    @Test
    void shouldFind<%= entityName %>ById() throws Exception {
        Long <%= entityVarName %>Id = 1L;
        <%= entityName %>Mapper <%= entityVarName %> = new <%= entityName %>Mapper(<%= entityVarName %>Id, "text 1");
        given(<%= entityVarName %>Service.buscarPorCodigo(<%= entityVarName %>Id)).willReturn(<%= entityVarName %>);

        this.mockMvc
                .perform(get("<%= basePath %>/{id}", <%= entityVarName %>Id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is(<%= entityVarName %>.getText())));
    }

    @Test
    void shouldReturn404WhenFetchingNonExisting<%= entityName %>() throws Exception {
        Long <%= entityVarName %>Id = 1L;
        given(<%= entityVarName %>Service.buscarPorCodigo(<%= entityVarName %>Id)).willReturn(null);

        this.mockMvc
                .perform(get("<%= basePath %>/{id}", <%= entityVarName %>Id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNew<%= entityName %>() throws Exception {
        given(<%= entityVarName %>Service.salvar<%= entityName %>(any(<%= entityName %>Mapper.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        <%= entityName %>Mapper <%= entityVarName %> = new <%= entityName %>Mapper(1L, "some text");
        this.mockMvc
                .perform(
                        post("<%= basePath %>")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(<%= entityVarName %>)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.text", is(<%= entityVarName %>.getText())));
    }


    @Test
    void shouldDelete<%= entityName %>() throws Exception {
        Long <%= entityVarName %>Id = 1L;
        <%= entityName %>Mapper <%= entityVarName %> = new <%= entityName %>Mapper(<%= entityVarName %>Id, "Some text");
        given(<%= entityVarName %>Service.buscarPorCodigo(<%= entityVarName %>Id)).willReturn(<%= entityVarName %>);
        doNothing().when(<%= entityVarName %>Service).remover<%= entityName %>(<%= entityVarName %>.getId());

        this.mockMvc
                .perform(delete("<%= basePath %>/{id}", <%= entityVarName %>.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is(<%= entityVarName %>.getText())));
    }

    @Test
    void shouldReturn404WhenDeletingNonExisting<%= entityName %>() throws Exception {
        Long <%= entityVarName %>Id = 1L;
        given(<%= entityVarName %>Service.buscarPorCodigo(<%= entityVarName %>Id)).willReturn(null);

        this.mockMvc
                .perform(delete("<%= basePath %>/{id}", <%= entityVarName %>Id))
                .andExpect(status().isNotFound());
    }
}
