package com.example.webprojekt.repositories;

import com.example.webprojekt.entities.ACmotor;
import com.example.webprojekt.entities.DCmotor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DCmotorRepository extends JpaRepository<DCmotor, Long> {
    @Query("SELECT d FROM DCmotor d WHERE d.id = ?1")
    DCmotor findDCmotorById(Long id);
}
