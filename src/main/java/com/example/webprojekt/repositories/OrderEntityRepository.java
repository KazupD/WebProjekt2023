package com.example.webprojekt.repositories;

import com.example.webprojekt.entities.OrderEntity;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {


    @Query(value="SELECT oe FROM OrderEntity oe WHERE oe.customer.id = ?1")
    List<OrderEntity> getOrdersByCustomerId(Long customer_id);

    @Query(value="SELECT oe FROM OrderEntity oe WHERE (oe.customer.id = ?1 AND oe.completed = false)")
    List<OrderEntity> getNotCompletedOrdersByCustomerId(Long customer_id);

}
