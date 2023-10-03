package com.example.webprojekt.repositories;

import com.example.webprojekt.entities.Admin;
import com.example.webprojekt.entities.AdminToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminTokenRepository extends JpaRepository<AdminToken, String> {
    AdminToken findFirstBy();
}
