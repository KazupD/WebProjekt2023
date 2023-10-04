package com.example.webprojekt.controllers;

import com.example.webprojekt.services.ShopManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/admin")
    public String showAdminlogin() {
        return "admin";
    }

    @GetMapping("/cart")
    public String showCart() {

        return "cart";
    }

    @GetMapping("/customer")
    public String showCustomer() {

        return "customer";
    }

    @GetMapping("/about")
    public String showAbout() {

        return "about";
    }
}
