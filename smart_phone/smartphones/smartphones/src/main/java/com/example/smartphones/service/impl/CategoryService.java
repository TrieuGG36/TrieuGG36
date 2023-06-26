package com.example.smartphones.service.impl;

import com.example.smartphones.entity.CategoryEntity;
import com.example.smartphones.repository.CategoryRepo;
import com.example.smartphones.service.ICategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategory {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<CategoryEntity> findByCategoryAndDeleteFlagIsFalse() {
        return categoryRepo.findByDeleteFlagIsFalse();
    }
}
