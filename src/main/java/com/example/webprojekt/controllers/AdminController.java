package com.example.webprojekt.controllers;

import com.example.webprojekt.entities.*;
import com.example.webprojekt.services.ShopManager;
import org.json.JSONArray;
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

import static org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Conversions.toLong;

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

    @PostMapping ("/getallorders")
    @ResponseBody
    public String getAllNotCompletedOrder() throws JSONException {

        Iterable<Customer> admin_customer_list = shopManager.getAllCustomer();
        JSONArray response_array = new JSONArray();
        for(Customer c : admin_customer_list){
            Long c_id = c.getId();

            JSONObject customer_details_and_orders_object = new JSONObject();

            JSONObject customer_details_object = new JSONObject();
            customer_details_object.put("first_name",c.getFirst_name());
            customer_details_object.put("last_name",c.getLast_name());
            customer_details_object.put("email",c.getEmail());
            customer_details_object.put("tel_num",c.getTelephone());
            customer_details_object.put("shipping_city",c.getShipping_city());
            customer_details_object.put("shipping_street",c.getShipping_street());
            customer_details_object.put("shipping_building",c.getShipping_building());

            customer_details_and_orders_object.put("customer_details", customer_details_object);

            JSONArray customer_orders_array = new JSONArray();
            Iterable<OrderEntity> c_order_list = shopManager.getNotCompletedOrdersByCustomerId(c_id);

            for(OrderEntity oe : c_order_list){
                JSONObject ordered_product = new JSONObject();

                Product p = shopManager.findProductById(oe.getId());

                ordered_product.put("product_id", p.getId());
                ordered_product.put("product_name", p.getName());
                ordered_product.put("quantity", oe.getQuantity());
                ordered_product.put("price", p.getPrice());
                ordered_product.put("price_sum", oe.getTotal_price());

                customer_orders_array.put(ordered_product);
            }

            customer_details_and_orders_object.put("customer_orders", customer_orders_array);

            response_array.put(customer_details_and_orders_object);
        }

        return response_array.toString();
    }

    @PostMapping ("/markascompleted")
    @ResponseBody
    public String setOrderAsCompleted(@RequestBody String request) throws JSONException {

        JSONObject jsonobj = new JSONObject(request);
        Long id = jsonobj.getLong("customer_id");

        Iterable<OrderEntity> c_order_list = shopManager.getOrdersByCustomerId(id);

        for(OrderEntity oe : c_order_list){
            oe.setCompleted(true);
            shopManager.saveOrder(oe);
        }
        return "OK";
    }
}
