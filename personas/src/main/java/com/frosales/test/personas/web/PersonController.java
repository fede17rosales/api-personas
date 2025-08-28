package com.frosales.test.personas.web;

import com.frosales.test.personas.dto.PersonRequest;
import com.frosales.test.personas.dto.PersonResponse;
import com.frosales.test.personas.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/persona")
public class PersonController {
    private final PersonService service;
    public PersonController(PersonService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponse create(@Validated @RequestBody PersonRequest request) {
        return service.create(request);
    }

    @GetMapping
    public Page<PersonResponse> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @PageableDefault(size = 10) Pageable pageable) {
        return service.search(name, email, pageable);
    }

    @PutMapping("/{id}")
    public PersonResponse update(@PathVariable Long id,
                                 @Validated @RequestBody PersonRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
