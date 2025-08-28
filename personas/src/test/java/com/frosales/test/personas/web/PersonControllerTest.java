package com.frosales.test.personas.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frosales.test.personas.dto.PersonRequest;
import com.frosales.test.personas.dto.PersonResponse;
import com.frosales.test.personas.security.NoSecurityConfig;
import com.frosales.test.personas.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
@Import(NoSecurityConfig.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPerson_ShouldReturnCreated() throws Exception {
        PersonRequest request = new PersonRequest("Federico", "fede@test.com");
        PersonResponse response = new PersonResponse(1L, "Federico", "fede@test.com", null,null);

        when(service.create(any(PersonRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/persona")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Federico"))
                .andExpect(jsonPath("$.email").value("fede@test.com"));
    }

    @Test
    void searchPeople_ShouldReturnList() throws Exception {
        PersonResponse response = new PersonResponse(1L, "Federico", "fede@test.com",null,null);
        when(service.search(eq("Federico"), eq("fede@test.com"), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(response)));

        mockMvc.perform(get("/api/v1/persona")
                        .param("name", "Federico")
                        .param("email", "fede@test.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].name").value("Federico"))
                .andExpect(jsonPath("$.content[0].email").value("fede@test.com"));
    }

    @Test
    void updatePerson_ShouldReturnUpdated() throws Exception {
        PersonRequest request = new PersonRequest("Fede Updated", "fede.updated@test.com");
        PersonResponse response = new PersonResponse(1L, "Fede Updated", "fede.updated@test.com", null,null);

        when(service.update(eq(1L), any(PersonRequest.class))).thenReturn(response);

        mockMvc.perform(put("/api/v1/persona/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Fede Updated"))
                .andExpect(jsonPath("$.email").value("fede.updated@test.com"));
    }

    @Test
    void deletePerson_ShouldReturnNoContent() throws Exception {
        doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/api/v1/persona/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}