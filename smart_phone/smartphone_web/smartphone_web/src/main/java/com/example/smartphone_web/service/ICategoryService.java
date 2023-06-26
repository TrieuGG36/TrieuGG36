package com.example.smartphone_web.service;


import com.example.smartphone_web.dto.request.category.CategoryReqDto;
import com.example.smartphone_web.dto.respone.category.CategoryDTO;
import com.example.smartphone_web.dto.respone.category.CategoryResponeDto;
import com.example.smartphone_web.entity.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponeDto> getAllCategory();
    CategoryEntity findById(String id);
    void saveCategory(CategoryReqDto categoryDto);
    String updateCategory(CategoryDTO categoryDto);
    void deleteCategory(CategoryEntity categoryEntity);
}
