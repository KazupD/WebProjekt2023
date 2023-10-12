package com.example.webprojekt.services;

import com.example.webprojekt.entities.Admin;
import com.example.webprojekt.entities.AdminToken;
import com.example.webprojekt.entities.Product;
import com.example.webprojekt.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopManager {
    private final AdminRepository adminRepo;
    private final AdminTokenRepository adminTokenRepo;
    private final ProductRepository productRepo;
    private final ACmotorRepository acmotorRepo;
    private final DCmotorRepository dcmotorRepo;

    @Autowired
    public ShopManager(AdminRepository adminRepo, AdminTokenRepository adminTokenRepo, ProductRepository productRepo, ACmotorRepository acmotorRepo, DCmotorRepository dcmotorRepo) {
        this.adminRepo = adminRepo;
        this.adminTokenRepo = adminTokenRepo;
        this.productRepo = productRepo;
        this.acmotorRepo = acmotorRepo;
        this.dcmotorRepo = dcmotorRepo;
    }

    public Iterable<Admin> getAllAdmins(){
        return adminRepo.findAll();
    }

    public Admin getAdminByNameAndPwd(String aname, String apwd){
        return adminRepo.findAdminByAdminnameAndAdminpwd(aname=aname, apwd=apwd);
    }

    public AdminToken getToken() {return adminTokenRepo.findFirstBy();}

    public Iterable<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Iterable<Product> getProductsByType(String type){
        return productRepo.findByTypeEquals(type);
    }

    public Iterable<Product> getProductsByPower(Float power_low, Float power_high){
        return productRepo.findByPowerGreaterThanAndPowerLessThan(power_low, power_high);
    }

    public Iterable<Product> getProductsByPrice(Float price_low, Float price_high){
        return productRepo.findByPriceGreaterThanAndPowerLessThan(price_low, price_high);
    }

    public Iterable<Product> getProductsByAllFilters(String type, Float power_low, Float power_high, Float price_low, Float price_high){
        return productRepo.findByAllFilters(type, power_low, power_high, price_low, price_high);
    }

    public Iterable<Product> getProductsByPowerAndPrice(Float power_low, Float power_high, Float price_low, Float price_high){
        return productRepo.findByPowerAndPrice(power_low, power_high, price_low, price_high);
    }

}
