package com.example.webprojekt.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image_name;
    private String name;
    private Float price;
    private String brand;
    private Float power;
    private String type;


    @OneToOne(mappedBy = "product")
    private ACmotor ACmotor;

    @OneToOne(mappedBy = "product")
    private DCmotor DCmotor;

    public Long getId() {
        return id;
    }

    public String getImage_name() {
        return image_name;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public Float getPower() {
        return power;
    }

    public String getType() {
        return type;
    }

    public com.example.webprojekt.entities.ACmotor getACmotor() {
        return ACmotor;
    }

    public com.example.webprojekt.entities.DCmotor getDCmotor() {
        return DCmotor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPower(Float power) {
        this.power = power;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setACmotor(com.example.webprojekt.entities.ACmotor ACmotor) {
        this.ACmotor = ACmotor;
    }

    public void setDCmotor(com.example.webprojekt.entities.DCmotor DCmotor) {
        this.DCmotor = DCmotor;
    }
}
