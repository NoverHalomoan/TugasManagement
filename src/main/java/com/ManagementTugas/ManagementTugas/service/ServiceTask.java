package com.ManagementTugas.ManagementTugas.service;

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
import com.ManagementTugas.ManagementTugas.repository.RepositoryCustomeTask;
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

    private static MapperAllDatas mapperAllDatas = new MapperAllDatas();

    private final AbstractRepository abstractRepository;
    private final UserRepository userRepository;
    private final GeneratedToken generatedToken;
    private final RepositoryCustomeTask repositoryCustomeTask;

    public ServiceTask(AbstractRepository abstractRepository, GeneratedToken generatedToken,
            UserRepository userRepository, RepositoryCustomeTask repositoryCustomeTask) {
        this.abstractRepository = abstractRepository;
        this.generatedToken = generatedToken;
        this.userRepository = userRepository;
        this.repositoryCustomeTask = repositoryCustomeTask;
    }

    public ApiResponseData ResponseInsert(String status, String message, Object Data) {
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
        Users datausers = userRepository.findByiduser(createTugasBaru.id_user()).get();
        Projects projects = abstractRepository.getProjctbyid(createTugasBaru.id_project()).get();

        if (tokendata.isEmpty() || tokendata.isBlank() || tokendata == "") {
            message = "Token tidak tidak valid atau kadaluwarsa";
            status = "403";
            datas = null;
        } else {
            // cek kelengkapan data
            TugasSaya tugasSaya = mapperAllDatas.cretaeTugasSaya(createTugasBaru);
            tugasSaya.setUsers(datausers);
            tugasSaya.setProjects(projects);
            TugasSaya temptugas = repositoryCustomeTask.save(tugasSaya);
            System.out.println("Nama user" + tugasSaya.getNamatugas());
            datas = temptugas;

        }
        return ResponseInsert(status, message, datas);
    }

}
