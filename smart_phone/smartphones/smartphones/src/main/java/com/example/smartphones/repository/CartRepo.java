package com.example.smartphones.repository;

import com.example.smartphones.entity.CartEntity;
import com.example.smartphones.entity.ProductPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<CartEntity, Long> {

    List<CartEntity> findByDeleteFlagIsFalseAndIdCustomer(Long id);

    CartEntity findByDeleteFlagIsFalseAndIdCustomerAndIdProduct(Long id, ProductPropertyEntity productPropertyEntity);

    CartEntity findByDeleteFlagIsFalseAndId(Long id);
}
