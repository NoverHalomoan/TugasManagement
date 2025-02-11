package com.ManagementTugas.ManagementTugas.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(schema = "DATATASK", name = "users")
public class Users {

    @Id
    @Column(name = "iduser")
    private String iduser;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Email
    private String email;
    @Column(nullable = false)
    private String password;

    // @JsonInclude(JsonInclude.Include.NON_EMPTY) // diugunakan supaya dta ngak ke
    // ikut
    @JsonIgnore
    private String tokenlogin;

    private String profile_image;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<TugasSaya> tugasSayaList;

    public List<TugasSaya> getTugasSayaList() {
        return tugasSayaList;
    }

    public void setTugasSayaList(List<TugasSaya> tugasSayaList) {
        this.tugasSayaList = tugasSayaList;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public Users() {
    }

    public Users(String iduser, String name, String email, String password) {
        this.iduser = iduser;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getTokenlogin() {
        return tokenlogin;
    }

    public void setTokenlogin(String tokenlogin) {
        this.tokenlogin = tokenlogin;
    }

}
