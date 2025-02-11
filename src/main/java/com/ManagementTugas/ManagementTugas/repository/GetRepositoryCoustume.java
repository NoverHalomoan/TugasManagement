package com.ManagementTugas.ManagementTugas.repository;

import java.util.Optional;

import com.ManagementTugas.ManagementTugas.model.Users;

import jakarta.persistence.EntityManager;

public class GetRepositoryCoustume implements CustomeRepository {

    private EntityManager entityManager;

    @Override
    public Optional<Users> findByTokenlogin(String token) {
        String sql = "SELECT u FROM Users u WHERE u.token = :token";
        return entityManager.createQuery(sql, Users.class).setParameter("token", token).getResultStream().findAny();
    }

}
