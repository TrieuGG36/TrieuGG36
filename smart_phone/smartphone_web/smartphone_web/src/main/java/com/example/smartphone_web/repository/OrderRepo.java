package com.example.smartphone_web.repository;

import com.example.smartphone_web.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<OrdersEntity, Long> {

    List<OrdersEntity> findByDeleteFlagIsFalseOrderByCreateDateDesc();


    OrdersEntity findByCodeOrderAndDeleteFlagIsFalse(String id);
}
