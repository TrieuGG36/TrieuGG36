package com.spring.api.reposirory;

import com.spring.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByproductName(String productName);
}
