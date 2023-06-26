package com.example.smartphones.repository;

import com.example.smartphones.entity.CamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CamRepo extends JpaRepository<CamEntity, Long> {
    List<CamEntity> findByDeleteFlagIsFalse();
    CamEntity findByIdAndDeleteFlagIsFalse(Long id);
}
