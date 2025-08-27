package com.frosales.test.personas.service;

import com.frosales.test.personas.domain.Person;
import com.frosales.test.personas.dto.PersonRequest;
import com.frosales.test.personas.dto.PersonResponse;
import com.frosales.test.personas.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.domain.*;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    private PersonRepository personRepository;
    private PersonMapper personMapper;
    private PersonService personService;

    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepository.class);
        personMapper = mock(PersonMapper.class);
        personService = new PersonService(personRepository, personMapper);
    }

    @Test
    void create_ShouldSaveAndReturnResponse() {
        PersonRequest request = new PersonRequest("Federico", "fede@test.com");
        Person saved = new Person("Federico", "fede@test.com");
        saved.setId(1L);

        when(personRepository.save(any(Person.class))).thenReturn(saved);

        PersonResponse response = personService.create(request);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("Federico");
        assertThat(response.getEmail()).isEqualTo("fede@test.com");

        verify(personRepository).save(any(Person.class));
    }


    @Test
    void update_ShouldModifyPerson_WhenExists() {
        PersonRequest request = new PersonRequest("Fede Updated", "updated@test.com");
        Person existing = new Person("Federico", "fede@test.com");
        existing.setId(1L);

        Person updated = new Person("Fede Updated", "updated@test.com");
        updated.setId(1L);

        PersonResponse response = new PersonResponse(1L, "Fede Updated", "updated@test.com", Instant.now(), Instant.now());

        when(personRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(personRepository.save(any(Person.class))).thenReturn(updated);
        when(personMapper.toResponse(updated)).thenReturn(response);

        PersonResponse result = personService.update(1L, request);

        assertThat(result.getName()).isEqualTo("Fede Updated");
        assertThat(result.getEmail()).isEqualTo("updated@test.com");
        verify(personRepository).save(existing);
    }

    @Test
    void update_ShouldThrowException_WhenNotFound() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        PersonRequest request = new PersonRequest("New", "new@test.com");

        assertThatThrownBy(() -> personService.update(1L, request))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Person not found with id 1");
    }

    @Test
    void delete_ShouldCallRepository_WhenExists() {
        when(personRepository.existsById(1L)).thenReturn(true);
        doNothing().when(personRepository).deleteById(1L);

        personService.delete(1L);

        verify(personRepository).deleteById(1L);
    }

    @Test
    void delete_ShouldThrowException_WhenNotFound() {
        when(personRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> personService.delete(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Person not found with id 1");
    }
}