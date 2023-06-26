package com.example.smartphones.service;

import com.example.smartphones.dto.respone.product.ProductPropertyRespone;

import java.util.List;

public interface IProductProperty {

    List<ProductPropertyRespone> findByRomId(Long romId);
}
