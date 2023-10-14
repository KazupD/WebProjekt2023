package com.example.webprojekt.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;
    private String last_name;
    private String email;
    private String telephone;
    private String shipping_city;
    private String shipping_street;
    private String shipping_building;

    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getShipping_city() {
        return shipping_city;
    }

    public String getShipping_street() {
        return shipping_street;
    }

    public String getShipping_building() {
        return shipping_building;
    }

    public Customer()
    {

    }

    public Customer(String first_name, String last_name, String email, String telephone, String shipping_city, String shipping_street, String shipping_building) {
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.telephone=telephone;
        this.shipping_city=shipping_city;
        this.shipping_street=shipping_street;
        this.shipping_building=shipping_building;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setShipping_city(String shipping_city) {
        this.shipping_city = shipping_city;
    }

    public void setShipping_street(String shipping_street) {
        this.shipping_street = shipping_street;
    }

    public void setShipping_building(String shipping_building) {
        this.shipping_building = shipping_building;
    }

}
