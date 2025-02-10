package com.ManagementTugas.ManagementTugas.model;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.Email;

@Hidden
public class LoginDTO {
    @Email(message = "Format email tidak sesuai")
    private String email;
    @Length(min = 8, message = "Password minimal 8 Characters")
    private String password;

    public LoginDTO() {

    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
