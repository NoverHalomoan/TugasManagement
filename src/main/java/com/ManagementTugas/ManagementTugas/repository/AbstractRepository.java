package com.ManagementTugas.ManagementTugas.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.ManagementTugas.ManagementTugas.model.ProjectDTO;
import com.ManagementTugas.ManagementTugas.model.Projects;

@Repository
public class AbstractRepository {

    protected final JdbcClient jdbcClient;

    public AbstractRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Optional<Projects> getProjectName(String namaprojects) {
        return jdbcClient.sql("select * from datatask.projects where name= :nama").param("nama", namaprojects)
                .query(Projects.class).optional();
    }

    public Optional<Projects> getProjctbyid(Integer idproject) {
        return jdbcClient.sql("select * from datatask.projects where id_project= :id").param("id", idproject)
                .query(Projects.class).optional();
    }

    public Projects addingProject(ProjectDTO projectDTO) {
        Projects projects = getProjectName(projectDTO.nama()).get();

        if (!projects.getId_project().toString().isEmpty()) {
            return projects;
        } else {
            var updated = jdbcClient.sql("Insert into datatask.Projects(name, description, created_at) values(?,?,?)")
                    .params(List.of(projectDTO.nama(), projectDTO.description(), LocalDateTime.now())).update();
            projects = getProjectName(projectDTO.nama()).get();

        }
        return projects;
    }

}
