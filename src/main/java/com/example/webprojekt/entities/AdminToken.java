package com.example.webprojekt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "admintoken")
public class AdminToken {
    @Id
    @NotNull
    private String token;

    public String getAdminTokenName() {
        return token;
    }
}
