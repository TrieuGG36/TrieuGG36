package com.example.smartphone_web.service;


import com.example.smartphone_web.dto.request.attribute.AttributeRequestAdd;
import com.example.smartphone_web.dto.request.attribute.AttributeRequestEdit;
import com.example.smartphone_web.dto.respone.attribute.AttributeRespone;

public interface IAttributeProduct {
    AttributeRespone findByProduct(Long id);

    String saveAttribute(AttributeRequestAdd requestAdd, Long productId) ;

    String updateAttribute(AttributeRequestEdit attributeRequestEdit, Long productId);
}
