package com.example.webprojekt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @NotNull
    private String adminname;
    @NotNull
    private String adminpwd;

    public String getAdminName() {
        return adminname;
    }

    public String getAdminPwd() {
        return adminpwd;
    }

}
