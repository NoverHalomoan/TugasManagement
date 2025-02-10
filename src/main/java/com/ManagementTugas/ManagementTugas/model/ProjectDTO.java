package com.ManagementTugas.ManagementTugas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public record ProjectDTO(
                String nama, String description) {

}
