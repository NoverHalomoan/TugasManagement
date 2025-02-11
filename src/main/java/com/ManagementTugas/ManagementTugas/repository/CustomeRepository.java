package com.ManagementTugas.ManagementTugas.repository;

import java.util.Optional;

import com.ManagementTugas.ManagementTugas.model.Users;

public interface CustomeRepository {

    Optional<Users> findByTokenlogin(String token);

}
