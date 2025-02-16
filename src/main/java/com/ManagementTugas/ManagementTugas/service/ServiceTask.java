package com.ManagementTugas.ManagementTugas.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ManagementTugas.ManagementTugas.DTOS.CreateTugasBaru;
import com.ManagementTugas.ManagementTugas.DTOS.MapperAllDatas;
import com.ManagementTugas.ManagementTugas.controller.ApiResponseData;
import com.ManagementTugas.ManagementTugas.model.ProjectDTO;
import com.ManagementTugas.ManagementTugas.model.Projects;
import com.ManagementTugas.ManagementTugas.model.TugasSaya;
import com.ManagementTugas.ManagementTugas.model.Users;
import com.ManagementTugas.ManagementTugas.repository.AbstractRepository;
import com.ManagementTugas.ManagementTugas.repository.ProjectsRepository;
import com.ManagementTugas.ManagementTugas.repository.RepositoryCustomeTask;
import com.ManagementTugas.ManagementTugas.repository.TaskRepository;
import com.ManagementTugas.ManagementTugas.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Service
@Validated
public class ServiceTask {
    String status;
    String message;
    Object datas;

    private static ApiResponseData<Object> apiResponseData = new ApiResponseData<>();

    private final MapperAllDatas mapperAllDatas;

    private final AbstractRepository abstractRepository;
    private final UserRepository userRepository;
    private final GeneratedToken generatedToken;
    private final RepositoryCustomeTask repositoryCustomeTask;
    private TaskRepository taskRepository;

    public ServiceTask(AbstractRepository abstractRepository, GeneratedToken generatedToken,
            UserRepository userRepository, RepositoryCustomeTask repositoryCustomeTask, MapperAllDatas mapperAllDatas,
            TaskRepository taskRepository) {
        this.abstractRepository = abstractRepository;
        this.generatedToken = generatedToken;
        this.userRepository = userRepository;
        this.repositoryCustomeTask = repositoryCustomeTask;
        this.mapperAllDatas = mapperAllDatas;
        this.taskRepository = taskRepository;
    }

    public ApiResponseData<Object> ResponseInsert(String status, String message, Object Data) {
        apiResponseData.setMessage(message);
        apiResponseData.setStatus(status);
        apiResponseData.setData(Data);
        return apiResponseData;
    }

    // adding new Projects
    public ApiResponseData<?> addnewproject(HttpServletRequest httpServletRequest, ProjectDTO projectDTO) {
        if (generatedToken.adatokendata(httpServletRequest) == 0) {
            message = "Token tidak tidak valid atau kadaluwarsa";
            status = "403";
            datas = null;

            return ResponseInsert(status, message, datas);
        }
        String tokendata = GeneratedToken.gettokenvalidasi(httpServletRequest);
        if (tokendata.isEmpty() || tokendata.isBlank() || tokendata == "") {
            message = "Token tidak tidak valid atau kadaluwarsa";
            status = "403";
            datas = null;

            return ResponseInsert(status, message, datas);
        } else {
            apiResponseData.setMessage("Project Sukses Dibuat");
            apiResponseData.setStatus("200");
            apiResponseData.setData(abstractRepository.addingProject(projectDTO));
        }

        return apiResponseData;

    }

    public ApiResponseData<?> addnewTask(HttpServletRequest httpServletRequest,
            @Valid CreateTugasBaru createTugasBaru) {
        apiResponseData = new ApiResponseData<>();
        if (generatedToken.adatokendata(httpServletRequest) == 0) {
            message = "Token tidak tidak valid atau kadaluwarsa";
            status = "403";
            datas = null;

            return ResponseInsert(status, message, datas);
        }
        String tokendata = GeneratedToken.gettokenvalidasi(httpServletRequest);
        Users datausers = userRepository.findByiduser(createTugasBaru.id_user()).isPresent()
                ? userRepository.findByiduser(createTugasBaru.id_user()).get()
                : null;
        Projects projects = abstractRepository.getProjctbyid(createTugasBaru.id_project()).isPresent()
                ? abstractRepository.getProjctbyid(createTugasBaru.id_project()).get()
                : null;
        if (datausers == null) {
            return ResponseInsert("403", "Data User Tidak Di Temukan", datausers);
        } else if (projects == null) {
            return ResponseInsert("403", "Data Project Tidak Di Temukan", projects);
        } else if (tokendata.isEmpty() || tokendata.isBlank() || tokendata == "") {
            message = "Token tidak tidak valid atau kadaluwarsa";
            status = "403";
            datas = null;
        } else {
            // cek kelengkapan data
            TugasSaya tugasSaya = mapperAllDatas.cretaeTugasSaya(createTugasBaru);
            tugasSaya.setUsers(datausers);
            tugasSaya.setProjects(projects);
            taskRepository.save(tugasSaya);
            datas = tugasSaya;
            message = "Tugas Sukses DiInsert";
            status = "200";

        }
        return ResponseInsert(status, message, datas);
    }

    // for get all projects
    public ApiResponseData<?> getallprojects(HttpServletRequest httpServletRequest) {
        apiResponseData = new ApiResponseData<>();
        if (generatedToken.adatokendata(httpServletRequest) == 0) {
            message = "Token tidak tidak valid atau kadaluwarsa";
            status = "403";
            datas = null;

            return ResponseInsert(status, message, datas);
        }
        String tokendata = GeneratedToken.gettokenvalidasi(httpServletRequest);
        if (tokendata.isEmpty() || tokendata.isBlank() || tokendata == "") {
            message = "Token tidak tidak valid atau kadaluwarsa";
            status = "403";
            datas = null;
        }
        // DateTimeFormatter dtformatter = DateTimeFormatter()
        List<Projects> allprojects = abstractRepository.getallprojects();
        // allprojects.forEach(dtproject ->
        // dtproject.setCreated_at(dtproject.getCreated_at()); );
        status = "200";
        message = "Sukses Ambil Data Projects";
        datas = allprojects;

        return ResponseInsert(status, message, datas);
    }

    public ApiResponseData<Object> searchtugas(String namtugas) {
        return ResponseInsert("200", "Sukses Search Data", repositoryCustomeTask.findBynamatugas(namtugas));
    }

}
