package com.example.smartphone_web.repository;


import com.example.smartphone_web.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findByDeleteFlagIsFalse();
    List<CategoryEntity> findByDeleteFlagIsFalseOrderByCreateDateAsc();


    List<CategoryEntity> findByIdAndDeleteFlagIsFalse(Long id);

    List<CategoryEntity> findByNameAndDeleteFlagIsFalse(String name);

}
