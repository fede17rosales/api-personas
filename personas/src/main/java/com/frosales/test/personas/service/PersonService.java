package com.frosales.test.personas.service;

import com.frosales.test.personas.domain.Person;
import com.frosales.test.personas.dto.PersonRequest;
import com.frosales.test.personas.dto.PersonResponse;
import com.frosales.test.personas.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;


@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper mapper;

    public PersonService(PersonRepository personRepository, PersonMapper mapper) {
        this.personRepository = personRepository;
        this.mapper = mapper;
    }

    @Transactional
    public PersonResponse create(PersonRequest request) {
        Person p = new Person(request.getName(), request.getEmail());
        p = personRepository.save(p);
        return toDto(p);
    }

    public Page<PersonResponse> search(String name, String email, Pageable pageable) {
        Specification<Person> spec = (root, cq, cb) -> {
            var predicates = new ArrayList<Predicate>();
            if (name != null && !name.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (email != null && !email.isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("email")), email.toLowerCase()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return personRepository.findAll(spec, pageable).map(this::toDto);

    }

    public PersonResponse update(Long id, PersonRequest request) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id " + id));
        person.setName(request.getName());
        person.setEmail(request.getEmail());
        return mapper.toResponse(personRepository.save(person));
    }

    public void delete(Long id) {
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException("Person not found with id " + id);
        }
        personRepository.deleteById(id);
    }


    private PersonResponse toDto(Person p) {
        return new PersonResponse(p.getId(), p.getName(), p.getEmail(), p.getCreatedAt(), p.getUpdatedAt());
    }

    }



