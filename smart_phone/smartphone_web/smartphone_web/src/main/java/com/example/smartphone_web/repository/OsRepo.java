package com.example.smartphone_web.repository;

import com.example.smartphone_web.entity.OSEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OsRepo extends JpaRepository<OSEntity, Long> {
    List<OSEntity> findByDeleteFlagIsFalse();
}
