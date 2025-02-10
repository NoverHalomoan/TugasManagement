package com.ManagementTugas.ManagementTugas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ManagementTugas.ManagementTugas.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>, CustomeRepository {

    boolean existsByname(String name);

    boolean existsByemail(String email);

    Optional<Users> findByiduser(String iduser);

    Optional<Users> findByemail(String email);

    // Find by email and password
    // Optional<Users> findByEmailAndPassword(String email, String password);

    // Custom JPQL Query
    // @Query("SELECT u FROM Users u WHERE u.email = :email AND u.password =
    // :password")
    // Optional<Users> findUserByEmailAndPassword(@Param("email") String email,
    // @Param("password") String password);

}
