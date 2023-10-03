package com.example.webprojekt.controllers;

import com.example.webprojekt.entities.Admin;
import com.example.webprojekt.services.ShopManager;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

}
