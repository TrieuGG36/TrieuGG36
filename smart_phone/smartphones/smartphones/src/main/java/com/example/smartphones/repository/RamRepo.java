package com.example.smartphones.repository;

import com.example.smartphones.entity.RamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RamRepo extends JpaRepository<RamEntity, Long> {

    List<RamEntity> findByDeleteFlagIsFalse();
}
