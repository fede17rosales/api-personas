package com.frosales.test.personas.service;

import com.frosales.test.personas.domain.Person;
import com.frosales.test.personas.dto.PersonRequest;
import com.frosales.test.personas.dto.PersonResponse;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public Person toEntity(PersonRequest request) {
        if (request == null) return null;
        Person person = new Person();
        person.setName(request.getName());
        person.setEmail(request.getEmail());
        return person;
    }

    public PersonResponse toResponse(Person entity) {
        if (entity == null) return null;
        return new PersonResponse(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
