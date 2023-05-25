package com.example.assjava5.repositories;

import com.example.assjava5.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {


    @Query("select o from Order o where o.status = 1 and o.statusShip = 0")
    public List<Order> findAllByStatus(Pageable pageable);

    @Query("select o from Order o where o.status = 1 and o.statusShip = 1")
    public List<Order> findAllByStatusShip(Pageable pageable);


    @Query("select o from Order o where o.status = 1 and o.statusShip = 2")
    public List<Order> findAllByDoneShip(Pageable pageable);
}
