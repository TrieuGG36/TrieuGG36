package com.example.smartphones.service;

import com.example.smartphones.dto.respone.attribute.AttributeRespone;


public interface IAttribute {
    AttributeRespone findByProduct(Long id);
}
