package com.example.webprojekt.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "dcmotor")
public class DCmotor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float nominal_voltage;
    private Float nominal_rpm;
    private Float power_factor;
    private Float steady_state_current;
    private Float stall_current;
    private Float steady_state_torque;
    private Float stall_torque;
    private Long commutator_segments;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public Float getNominal_voltage() {
        return nominal_voltage;
    }

    public Float getNominal_rpm() {
        return nominal_rpm;
    }

    public Float getPower_factor() {
        return power_factor;
    }

    public Float getSteady_state_current() {
        return steady_state_current;
    }

    public Float getStall_current() {
        return stall_current;
    }

    public Float getSteady_state_torque() {
        return steady_state_torque;
    }

    public Float getStall_torque() {
        return stall_torque;
    }

    public Long getCommutator_segments() {
        return commutator_segments;
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

    public void setNominal_rpm(Float nominal_rpm) {
        this.nominal_rpm = nominal_rpm;
    }

    public void setPower_factor(Float power_factor) {
        this.power_factor = power_factor;
    }

    public void setSteady_state_current(Float steady_state_current) {
        this.steady_state_current = steady_state_current;
    }

    public void setStall_current(Float stall_current) {
        this.stall_current = stall_current;
    }

    public void setSteady_state_torque(Float steady_state_torque) {
        this.steady_state_torque = steady_state_torque;
    }

    public void setStall_torque(Float stall_torque) {
        this.stall_torque = stall_torque;
    }

    public void setCommutator_segments(Long commutator_segments) {
        this.commutator_segments = commutator_segments;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
