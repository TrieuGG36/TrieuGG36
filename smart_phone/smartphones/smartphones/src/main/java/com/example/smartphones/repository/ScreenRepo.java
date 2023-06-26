package com.example.smartphones.repository;

import com.example.smartphones.entity.ScreenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepo extends JpaRepository<ScreenEntity, Long> {
    List<ScreenEntity> findByDeleteFlagIsFalse();
}
