package com.example.smartphone_web.repository;

import com.example.smartphone_web.entity.ScreenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepo extends JpaRepository<ScreenEntity, Long> {


    List<ScreenEntity> findByDeleteFlagIsFalse();
}
