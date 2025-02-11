package com.ManagementTugas.ManagementTugas.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.ManagementTugas.ManagementTugas.DTOS.MapperAllDatas;
import com.ManagementTugas.ManagementTugas.model.ProjectDTO;
import com.ManagementTugas.ManagementTugas.model.Projects;

@Repository
public class AbstractRepository {

    protected final JdbcClient jdbcClient;

    private final MapperAllDatas mapperAllDatas;

    private final ProjectsRepository projectsRepository;

    public AbstractRepository(JdbcClient jdbcClient, ProjectsRepository projectsRepository,
            MapperAllDatas mapperAllDatas) {
        this.jdbcClient = jdbcClient;
        this.projectsRepository = projectsRepository;
        this.mapperAllDatas = mapperAllDatas;
    }

    public Optional<Projects> getProjectName(String namaprojects) {
        return jdbcClient.sql(
                "select * from datatask.projects u where name= :nama")
                .param("nama", namaprojects)
                .query(Projects.class).optional();
    }

    public Optional<Projects> getProjctbyid(Integer idproject) {
        return jdbcClient.sql(
                "select * from datatask.projects where idproject= :id")
                .param("id", idproject)
                .query(Projects.class).optional();
    }

    // get all projects
    public List<Projects> getallprojects() {
        return projectsRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Projects addingProject(ProjectDTO projectDTO) {
        System.out.println("Error disini :" + projectDTO.nama());
        Optional<Projects> projects = getProjectName(projectDTO.nama());

        if (projects.isPresent()) {
            return projects.get();
        } else {
            Projects newproject = mapperAllDatas.createnewproject(projectDTO);
            projectsRepository.save(newproject);
            // var updated = jdbcClient.sql("Insert into datatask.Projects(name,
            // description, created_at) values(?,?,?)")
            // .params(List.of(projectDTO.nama(), projectDTO.description(),
            // LocalDateTime.now())).update();
            projects = getProjectName(projectDTO.nama());

        }
        return projects.get();
    }

}
