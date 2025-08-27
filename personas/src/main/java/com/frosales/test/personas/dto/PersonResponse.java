package com.frosales.test.personas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class PersonResponse {
    private Long id;
    private String name;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;

}
