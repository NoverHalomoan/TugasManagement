package com.ManagementTugas.ManagementTugas.repository;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.ManagementTugas.ManagementTugas.model.TugasSaya;

import jakarta.persistence.EntityManager;

@Repository
public class RepositoryCustomeTask implements InterCustomeTaskRepo {

    private final TaskRepository taskRepository;

    public RepositoryCustomeTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // @Override
    // public TugasSaya saveTugasSaya(TugasSaya tugasSaya) {

    // taskRepository.save(tugasSaya);
    // return tugasSaya;

    // }

    // @Override
    // public Optional<TugasSaya> findByid_tugas(String idtugas) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'findAllData'");
    // }

}
