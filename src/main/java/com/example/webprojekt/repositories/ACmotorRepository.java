package com.example.webprojekt.repositories;

import com.example.webprojekt.entities.ACmotor;
import com.example.webprojekt.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ACmotorRepository extends JpaRepository<ACmotor, Long> {
    @Query("SELECT a FROM ACmotor a WHERE a.id = ?1")
    ACmotor findACmotorById(Long id);
}
