package com.example.smartphone_web.repository.DAO;

import com.example.smartphone_web.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDAO extends JpaRepository<CustomerEntity, Integer> {

    List<CustomerEntity> findByPhoneNumber(String phoneNumer);
}