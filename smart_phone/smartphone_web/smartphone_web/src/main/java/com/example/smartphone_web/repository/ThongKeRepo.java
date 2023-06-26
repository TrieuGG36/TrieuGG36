package com.example.smartphone_web.repository;

import com.example.smartphone_web.dto.respone.ThongKeDto;
import com.example.smartphone_web.entity.OrdersDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThongKeRepo extends JpaRepository<OrdersDetailEntity, Long> {

    @Query(name = "thong_ke", nativeQuery = true)
    List<ThongKeDto> findStockAkhirPerProductIn(@Param("month") Integer month, @Param("year") Integer year);
}
