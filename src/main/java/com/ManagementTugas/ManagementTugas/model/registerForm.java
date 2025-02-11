package com.ManagementTugas.ManagementTugas.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record registerForm(

                @NotBlank(message = "Nama tidak boleh kosong") String name,
                @Email(message = "Format email tidak sesuai") @NotBlank(message = "Email tidak boleh kosong") String email,
                @Size(min = 8, message = "Password minimal 8 karakter") String password) {

}
