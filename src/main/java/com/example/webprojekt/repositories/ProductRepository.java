package com.example.webprojekt.repositories;

import com.example.webprojekt.entities.Admin;
import com.example.webprojekt.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    List<Product> findByPowerGreaterThanAndPowerLessThan(Float power_low, Float power_high);

    List<Product> findByTypeEquals(String type);

    List<Product> findByPriceGreaterThanAndPowerLessThan(Float price_low, Float price_high);

    @Query("SELECT p FROM Product p WHERE (p.type = ?1) AND (p.power BETWEEN ?2 AND ?3) AND (p.price BETWEEN ?4 AND ?5)")
    List<Product> findByAllFilters(String type, Float power_low, Float power_high, Float price_low, Float price_high);

    @Query("SELECT p FROM Product p WHERE (p.power BETWEEN ?1 AND ?2) AND (p.price BETWEEN ?3 AND ?4)")
    List<Product> findByPowerAndPrice(Float power_low, Float power_high, Float price_low, Float price_high);
}
