package com.example.webprojekt.services;


import com.example.webprojekt.entities.Admin;
import com.example.webprojekt.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopManager {
    private final AdminRepository adminRepo;

    @Autowired
    public ShopManager(AdminRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Iterable<Admin> getAllAdmins(){
        return adminRepo.findAll();
    }

}
