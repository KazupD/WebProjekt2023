package com.example.webprojekt.repositories;

import com.example.webprojekt.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    List<Admin> findAll();
}
