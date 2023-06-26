package com.example.smartphone_web.service;

import com.example.smartphone_web.dto.request.product.ProductRequestAdd;
import com.example.smartphone_web.dto.request.product.ProductRequestEdit;
import com.example.smartphone_web.dto.respone.product.ProductResponse;

import java.util.List;

public interface IProduct {

    List<ProductResponse> findAll();

    String createProduct(ProductRequestAdd requestProduct);

    String editProduct(ProductRequestEdit requestEdit);

    ProductResponse getProduct(Long id);

    List<ProductResponse> getName(String name);

    List<ProductResponse> getNameNhapHang(String name);

    String deleteProduct(Long id);

    String editStatusProduct(Long id, String value);
}
