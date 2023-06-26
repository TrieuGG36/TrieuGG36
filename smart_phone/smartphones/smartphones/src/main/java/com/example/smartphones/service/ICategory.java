package com.example.smartphones.service;

import com.example.smartphones.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategory {
    List<CategoryEntity> findByCategoryAndDeleteFlagIsFalse();
}
