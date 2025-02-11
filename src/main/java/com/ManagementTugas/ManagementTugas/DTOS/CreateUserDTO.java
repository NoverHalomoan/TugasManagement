package com.ManagementTugas.ManagementTugas.DTOS;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Hidden
public class CreateUserDTO {
    @NotBlank(message = "Nama tidak boleh kosong")
    private String name;
    @Email
    @Email(message = "Format email tidak sesuai")
    @NotBlank(message = "Email tidak boleh kosong")
    private String email;
    @Size(min = 8, message = "Password minimal 8 karakter")
    private String password;

    public CreateUserDTO() {
    }

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
