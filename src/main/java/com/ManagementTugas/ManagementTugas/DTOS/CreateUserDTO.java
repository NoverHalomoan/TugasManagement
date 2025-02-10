package com.ManagementTugas.ManagementTugas.DTOS;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;

@Hidden
public class CreateUserDTO {
    private String name;
    @Email
    private String email;
    private String password;

    public CreateUserDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
