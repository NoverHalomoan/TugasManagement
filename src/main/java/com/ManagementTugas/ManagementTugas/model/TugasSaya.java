package com.ManagementTugas.ManagementTugas.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "datatask", name = "tugassaya")
public class TugasSaya {
    @Id
    @JsonProperty("idtugas")
    @Column(name = "idtugas")
    private String idtugas;

    @JsonProperty("namatugas")
    private String namatugas;

    @JsonProperty("mulaitugas")
    private LocalDateTime mulaitugas;
    @JsonProperty("berakhirtugas")
    private LocalDateTime berakhirtugas;
    @JsonProperty("deskripsi")
    private String deskripsi;

    @JsonProperty("completed")
    private boolean completed;

    // @JsonIgnore
    // private Object TugasSaya;

    @ManyToOne
    @JoinColumn(name = "iduser")
    @JsonBackReference
    private Users users;

    @ManyToOne
    @JoinColumn(name = "idproject", referencedColumnName = "idproject")
    @JsonBackReference
    private Projects projects;

    public TugasSaya() {

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

    public String getIdtugas() {
        return idtugas;
    }

    public void setIdtugas(String idtugas) {
        this.idtugas = idtugas;
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
