package com.ManagementTugas.ManagementTugas.DTOS;

import org.springframework.stereotype.Component;

import com.ManagementTugas.ManagementTugas.model.Projects;
import com.ManagementTugas.ManagementTugas.model.Users;

@Component
public class MapperUser {
    public ResponseUserDTO responseUserDTO(Users users) {
        String iduser = users.getIduser();
        String email = users.getEmail();
        String password = users.getPassword();

        return new ResponseUserDTO(iduser, email, password);
    }

    public Users tousers(String newid, CreateUserDTO createUserDTO) {

        return new Users(newid, createUserDTO.getName(), createUserDTO.getEmail(), createUserDTO.getPassword());
    }

}
