package com.ManagementTugas.ManagementTugas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ManagementTugas.ManagementTugas.model.Projects;

public interface ProjectsRepository extends JpaRepository<Projects, Integer> {

}
