package com.ManagementTugas.ManagementTugas.model;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "projects", schema = "datatask")
public class Projects {
    @Id
    private Integer id_project;
    private String name;
    private String description;
    private Timestamp created_at;

    @OneToMany(mappedBy = "projects", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TugasSaya> tugasSayaList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId_project() {
        return id_project;
    }

    public void setId_project(Integer id_project) {
        this.id_project = id_project;
    }

    public List<TugasSaya> getTugasSayaList() {
        return tugasSayaList;
    }

    public void setTugasSayaList(List<TugasSaya> tugasSayaList) {
        this.tugasSayaList = tugasSayaList;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

}
