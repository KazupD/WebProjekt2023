package com.example.webprojekt.repositories;

import com.example.webprojekt.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {



    @Modifying
    @Query(value="UPDATE OrderEntity oe SET oe.completed = true WHERE oe.id = ?1")
    void setOrderAsCompleted(String order_id);
    List<OrderEntity> findAll();

    @Query(value="SELECT oe FROM OrderEntity oe WHERE oe.id = ?1")
    List<OrderEntity> getOrdersByCustomerId(Long id);

    @Query(value="SELECT oe FROM OrderEntity oe WHERE (oe.id = ?1 AND oe.completed = false)")
    List<OrderEntity> getNotCompletedOrdersByCustomerId(Long id);
}
