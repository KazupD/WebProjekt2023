package com.example.webprojekt.controllers;

import com.example.webprojekt.entities.Customer;
import com.example.webprojekt.entities.OrderEntity;
import com.example.webprojekt.entities.Product;
import com.example.webprojekt.services.ShopManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static java.lang.Float.parseFloat;

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

    @PostMapping("/getallproducts")
    @ResponseBody
    public String showAllProducts(@RequestBody String req) throws JSONException {
        Iterable<Product> query_result;

        query_result = shopManager.getAllProducts();

        JSONArray response = new JSONArray();

        for(Product p : query_result){
            JSONObject product_entity = new JSONObject();

            product_entity.put("id", p.getId());
            product_entity.put("name", p.getName());
            product_entity.put("image_name", p.getImage_name());
            product_entity.put("brand", p.getBrand());
            product_entity.put("power", p.getPower());
            product_entity.put("type", p.getType());
            product_entity.put("price", p.getPrice());
            response.put(product_entity);
        }

        return response.toString();
    }

    @PostMapping("/filter")
    @ResponseBody
    public String showFilteredProducts(@RequestBody String filter) throws JSONException {

        JSONObject jsonobj = new JSONObject(filter);

        String type = jsonobj.getString("type");
        String min_power = jsonobj.getString("min_power");
        String max_power = jsonobj.getString("max_power");
        String min_price = jsonobj.getString("min_price");
        String max_price = jsonobj.getString("max_price");

        Iterable<Product> query_result;

        if(type.equals("Any")){
            query_result = shopManager.getProductsByPowerAndPrice(parseFloat(min_power), parseFloat(max_power), parseFloat(min_price), parseFloat(max_price));
        } else {
            query_result = shopManager.getProductsByAllFilters(type, parseFloat(min_power), parseFloat(max_power), parseFloat(min_price), parseFloat(max_price));
        }

        JSONArray response = new JSONArray();

        for(Product p : query_result){
            JSONObject product_entity = new JSONObject();

            product_entity.put("id", p.getId());
            product_entity.put("name", p.getName());
            product_entity.put("image_name", p.getImage_name());
            product_entity.put("brand", p.getBrand());
            product_entity.put("power", p.getPower());
            product_entity.put("type", p.getType());
            product_entity.put("price", p.getPrice());
            response.put(product_entity);
        }

        return response.toString();
    }


    @PostMapping("/neworder")
    @ResponseBody
    public String addNewOrder(@RequestBody String order_params) throws JSONException {

        JSONObject jsonobj = new JSONObject(order_params);

        String first_name = jsonobj.getString("first_name");
        String last_name = jsonobj.getString("last_name");
        String email = jsonobj.getString("email");
        String tel_num = jsonobj.getString("tel_num");
        String shipping_city = jsonobj.getString("shipping_city");
        String shipping_street = jsonobj.getString("shipping_street");
        String shipping_building = jsonobj.getString("shipping_building");
        JSONArray order_list = jsonobj.getJSONArray("order_list");

        Long customer_id = shopManager.getCustomerIdByPersonalData(first_name, last_name, email, tel_num, shipping_city, shipping_street, shipping_building);
        System.out.println(customer_id);


        if(customer_id == null){
            System.out.println("Inserting new customer");
            Customer c = new Customer(first_name, last_name, email, tel_num, shipping_city, shipping_street, shipping_building);
            shopManager.saveCustomer(c);
            System.out.println("New customer inserted");
        }

        customer_id = shopManager.getCustomerIdByPersonalData(first_name, last_name, email, tel_num, shipping_city, shipping_street, shipping_building);
        System.out.println(customer_id);

        if(customer_id == null){
            System.out.println("Customer still not exist after insertion");
            return "ERROR";
        }

        for(int i = 0; i < order_list.length(); i++)
        {
            JSONObject order_row = order_list.getJSONObject(i);
            Long id = order_row.getLong("id");
            Long quantity = order_row.getLong("quantity");
            Float price_sum = shopManager.getPriceById(id)*quantity;

            Product p = shopManager.findProductById(id);
            Customer c = shopManager.findCustomerById(customer_id);

            OrderEntity oe = new OrderEntity(quantity, price_sum, c, p);

            shopManager.saveOrder(oe);
        }

        return "OK";
    }
}
