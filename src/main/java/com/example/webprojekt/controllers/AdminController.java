package com.example.webprojekt.controllers;

import com.example.webprojekt.entities.Admin;
import com.example.webprojekt.entities.AdminToken;
import com.example.webprojekt.services.ShopManager;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    private final ShopManager shopManager;


    public AdminController(ShopManager shopManager) {
        this.shopManager = shopManager;
    }

    @PostMapping ("/entercredentials")
    @ResponseBody
    public String showAdmin(@RequestBody String credentials) throws JSONException {

        JSONObject jsonobj = new JSONObject(credentials);

        String admin_name = jsonobj.getString("aname");
        String admin_password = jsonobj.getString("apwd");

        Admin findadmin = shopManager.getAdminByNameAndPwd(admin_name, admin_password);

        if(findadmin!=null){
            System.out.println("Admin found");
            AdminToken token = shopManager.getToken();
            return token.getAdminTokenName();
        }
        else{
            System.out.println("Admin not found");
            return "";
        }
    }
}
