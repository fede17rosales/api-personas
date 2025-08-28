package com.frosales.test.personas.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class PersonRequest {
    @NotBlank
    @Size(max = 120)
    private String name;
    @NotBlank @Email
    @Size(max = 160)
    private String email;

}
