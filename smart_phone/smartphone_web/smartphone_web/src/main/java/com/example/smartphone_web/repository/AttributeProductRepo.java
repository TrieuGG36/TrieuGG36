package com.example.smartphone_web.repository;

import com.example.smartphone_web.entity.AttributeProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeProductRepo extends JpaRepository<AttributeProductEntity, Long> {
    AttributeProductEntity findByProductId(Long productId);
}
