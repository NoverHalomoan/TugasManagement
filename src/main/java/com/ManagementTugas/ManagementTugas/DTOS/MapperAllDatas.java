package com.ManagementTugas.ManagementTugas.DTOS;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ManagementTugas.ManagementTugas.model.ProjectDTO;
import com.ManagementTugas.ManagementTugas.model.Projects;
import com.ManagementTugas.ManagementTugas.model.TugasSaya;
import com.ManagementTugas.ManagementTugas.service.GeneratedToken;

@Component
public class MapperAllDatas {

    private final GeneratedToken generatedToken;

    public MapperAllDatas(GeneratedToken generatedToken) {
        this.generatedToken = generatedToken;
    }

    public TugasSaya cretaeTugasSaya(CreateTugasBaru createTugasBaru) {
        TugasSaya tugasSaya = new TugasSaya();
        tugasSaya.setBerakhirtugas(createTugasBaru.berakhirtugas());
        tugasSaya.setCompleted(createTugasBaru.completed());
        tugasSaya.setDeskripsi(createTugasBaru.deskripsi());
        tugasSaya.setMulaitugas(createTugasBaru.mulaitugas());
        tugasSaya.setNamatugas(createTugasBaru.namatugas());
        tugasSaya.setIdtugas(generatedToken.generatedIdTugas());
        return tugasSaya;

    }

    public Projects createnewproject(ProjectDTO projectDTO) {
        Projects newprojects = new Projects(projectDTO.nama(), projectDTO.description(),
                Timestamp.valueOf(LocalDateTime.now()));
        return newprojects;
    }
}
