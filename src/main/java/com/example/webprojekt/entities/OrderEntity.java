package com.example.webprojekt.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "order_table")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    private Float total_price;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean completed = false;

    @NotNull
    @ManyToOne
    private Customer customer = new Customer();

    @NotNull
    @ManyToOne
    private Product product = new Product();

    public OrderEntity()
    {

    }

    public OrderEntity(Long quantity, Float total_price, Customer customer, Product product) {
        this.quantity = quantity;
        this.total_price = total_price;
        this.customer = customer;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Float getTotal_price() {
        return total_price;
    }

    public boolean getCompleted() {
        return completed;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
