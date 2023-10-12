package com.example.webprojekt.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "acmotor")
public class ACmotor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float nominal_voltage;
    private Float nominal_current;
    private Float nominal_rpm;
    private Long pole_pairs;
    private Long phase_number; // 1 vagy 3
    private Float cosine_phi;
    private Float slip;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public Float getNominal_voltage() {
        return nominal_voltage;
    }

    public Float getNominal_current() {
        return nominal_current;
    }

    public Float getNominal_rpm() {
        return nominal_rpm;
    }

    public Long getPole_pairs() {
        return pole_pairs;
    }

    public Long getPhase_number() {
        return phase_number;
    }

    public Float getCosine_phi() {
        return cosine_phi;
    }

    public Float getSlip() {
        return slip;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNominal_voltage(Float nominal_voltage) {
        this.nominal_voltage = nominal_voltage;
    }

    public void setNominal_current(Float nominal_current) {
        this.nominal_current = nominal_current;
    }

    public void setNominal_rpm(Float nominal_rpm) {
        this.nominal_rpm = nominal_rpm;
    }

    public void setPole_pairs(Long pole_pairs) {
        this.pole_pairs = pole_pairs;
    }

    public void setPhase_number(Long phase_number) {
        this.phase_number = phase_number;
    }

    public void setCosine_phi(Float cosine_phi) {
        this.cosine_phi = cosine_phi;
    }

    public void setSlip(Float slip) {
        this.slip = slip;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
