package com.example.smartphone_web.service;



import com.example.smartphone_web.dto.respone.category.CategoryDTO;
import com.example.smartphone_web.dto.respone.category.CategoryResponeDto;

import java.util.List;

public interface ICateService {
    List<CategoryResponeDto> findAll();

    String save(CategoryDTO request);

    String edit(CategoryDTO request);

    CategoryResponeDto findById(String id);

    String delete(Long id);
}
