package com.example.smartphone_web.repository;


import com.example.smartphone_web.entity.OrdersDetailEntity;
import com.example.smartphone_web.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrdersDetailRepo extends JpaRepository<OrdersDetailEntity, Long> {
    List<OrdersDetailEntity> findByDeleteFlagIsFalseAndOrdersEntity(OrdersEntity ordersEntity);

    OrdersDetailEntity findByDeleteFlagIsTrueAndId(Long id);

    OrdersDetailEntity findByDeleteFlagIsFalseAndId(Long id);

}
