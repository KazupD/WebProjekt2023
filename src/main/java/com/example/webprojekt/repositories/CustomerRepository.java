package com.example.webprojekt.repositories;

import com.example.webprojekt.entities.Customer;
import com.example.webprojekt.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query("SELECT c.id FROM Customer c WHERE c.first_name = ?1 AND c.last_name = ?2 AND c.email = ?3 AND c.telephone = ?4 AND c.shipping_city = ?5 AND c.shipping_street = ?6 AND c.shipping_building = ?7")
    Long getCustomerIdByPersonalData(String first_name, String last_name, String email, String tel_num, String shipping_city, String shipping_street, String shipping_building);

    List<Customer> findAll();

    @Query("SELECT c FROM Customer c WHERE c.id = ?1")
    Customer findCustomerById(Long id);
}
