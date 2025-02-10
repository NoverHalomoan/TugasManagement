package com.ManagementTugas.ManagementTugas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ManagementTugas.ManagementTugas.model.TugasSaya;

@Repository
public interface TaskRepository extends JpaRepository<TugasSaya, String>, InterCustomeTaskRepo {

}
