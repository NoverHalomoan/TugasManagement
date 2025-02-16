package com.ManagementTugas.ManagementTugas.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.ManagementTugas.ManagementTugas.model.TugasSaya;

import jakarta.persistence.EntityManager;

@Repository
public class RepositoryCustomeTask implements InterCustomeTaskRepo {

    private final TaskRepository taskRepository;

    public RepositoryCustomeTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TugasSaya> findBynamatugas(String namatugas) {
        return taskRepository.findBynamatugas(namatugas).stream().toList();
    }

}
