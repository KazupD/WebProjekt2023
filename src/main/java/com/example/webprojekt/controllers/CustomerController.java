package com.example.webprojekt.controllers;

import com.example.webprojekt.entities.Admin;
import com.example.webprojekt.services.ShopManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class CustomerController {

    private final ShopManager shopManager;

    public CustomerController(ShopManager shopManager) {
        this.shopManager = shopManager;
    }

    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/adminlogin")
    public String showAdminlogin() {
        return "adminlogin";
    }

    @GetMapping("/admin")
    public String showAdmin(@RequestParam("apwd") String aname,
                            @RequestParam("apwd") String apwd) {
        List<Admin> admins = (List<Admin>) shopManager.getAllAdmins();

        for (Admin i : admins) {
            if(i.getAdminName().equals(aname) && i.getAdminPwd().equals(apwd)){
                return "admin";
            }

        }

        return "adminlogin";
    }
}
