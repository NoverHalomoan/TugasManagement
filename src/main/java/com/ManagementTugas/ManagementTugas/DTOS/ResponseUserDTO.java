package com.ManagementTugas.ManagementTugas.DTOS;

public class ResponseUserDTO {
    private String Idusers;
    private String name;

    public String getIdusers() {
        return Idusers;
    }

    public ResponseUserDTO(String idusers, String name, String email) {
        Idusers = idusers;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    private String email;

}
