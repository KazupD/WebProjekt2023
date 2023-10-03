package com.example.webprojekt.services;


import com.example.webprojekt.entities.Admin;
import com.example.webprojekt.entities.AdminToken;
import com.example.webprojekt.repositories.AdminRepository;
import com.example.webprojekt.repositories.AdminTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopManager {
    private final AdminRepository adminRepo;
    private final AdminTokenRepository adminTokenRepo;

    @Autowired
    public ShopManager(AdminRepository adminRepo, AdminTokenRepository adminTokenRepo) {
        this.adminRepo = adminRepo;
        this.adminTokenRepo = adminTokenRepo;
    }

    public Iterable<Admin> getAllAdmins(){
        return adminRepo.findAll();
    }

    public Admin getAdminByNameAndPwd(String aname, String apwd){
        return adminRepo.findAdminByAdminnameAndAdminpwd(aname=aname, apwd=apwd);
    }

    public AdminToken getToken() {return adminTokenRepo.findFirstBy();}

}
