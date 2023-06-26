package com.example.smartphones.service;

import com.example.smartphones.dto.respone.product.NewProductRespone;
import com.example.smartphones.dto.respone.product.ProductRespone;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProduct {
    List<NewProductRespone> findAll(Pageable pageable);

    ProductRespone findById(Long id);

    List<NewProductRespone> findByCateId(Long id);

    List<NewProductRespone> findByIphone();

    List<NewProductRespone> findBySamSung();

    List<NewProductRespone> findByXiaomi();

    List<NewProductRespone> findByOppo();

    List<NewProductRespone> findByNokia();

    List<NewProductRespone> findbyTop10();

    List<NewProductRespone> findByName(String name);

    List<NewProductRespone> findbyRandom();
}
