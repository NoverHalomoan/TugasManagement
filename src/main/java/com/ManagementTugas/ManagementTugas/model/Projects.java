package com.ManagementTugas.ManagementTugas.model;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "projects", schema = "datatask")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idproject;
    private String name;
    private String description;
    private Timestamp created_at;

    public Projects() {

    }

    public Projects(String name, String description, Timestamp created_at) {
        this.name = name;
        this.description = description;
        this.created_at = created_at;
    }

    @OneToMany(mappedBy = "projects", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
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

    public Integer getIdproject() {
        return idproject;
    }

    public void setIdproject(Integer idproject) {
        this.idproject = idproject;
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
