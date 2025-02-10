package com.ManagementTugas.ManagementTugas.model;

import java.time.LocalDateTime;
import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(schema = "datatask", name = "tugassaya")
public class TugasSaya {
    @Id
    @JsonProperty("id_tugas")
    @Column(name = "id_tugas")
    private String id_tugas;

    public String getId_tugas() {
        return id_tugas;
    }

    public void setId_tugas(String id_tugas) {
        this.id_tugas = id_tugas;
    }

    @JsonProperty("namatugas")
    private String namatugas;

    @JsonProperty("mulaitugas")
    private LocalDateTime mulaitugas;
    @JsonProperty("berakhirtugas")
    private LocalDateTime berakhirtugas;
    @JsonProperty("deskripsi")
    private String deskripsi;

    public String getIde_tugas() {
        return ide_tugas;
    }

    public void setIde_tugas(String ide_tugas) {
        this.ide_tugas = ide_tugas;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    @JsonProperty("completed")
    private boolean completed;
    private String ide_tugas;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private Users users;

    @ManyToOne
    @JoinColumn(name = "id_project")
    @JsonBackReference
    private Projects projects;

    public String getNamatugas() {
        return namatugas;
    }

    public void setNamatugas(String namatugas) {
        this.namatugas = namatugas;
    }

    public LocalDateTime getMulaitugas() {
        return mulaitugas;
    }

    public void setMulaitugas(LocalDateTime mulaitugas) {
        this.mulaitugas = mulaitugas;
    }

    public LocalDateTime getBerakhirtugas() {
        return berakhirtugas;
    }

    public void setBerakhirtugas(LocalDateTime berakhirtugas) {
        this.berakhirtugas = berakhirtugas;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    //
    // @Transient
    // private static JdbcTemplate jdbcTemplate;

    // @PrePersist
    // public void getidtask() {
    // int year = Year.now().getValue() % 100;
    // Long nextval = jdbcTemplate.queryForObject("SELECT nextval('custom_id_seq')",
    // Long.class);
    // String sequenceFormat = String.format("%08d", nextval);

    // this.id_tugas = "T" + year + sequenceFormat;
    // }

}
