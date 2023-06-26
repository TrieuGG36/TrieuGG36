package com.example.smartphone_web.service;

import com.example.smartphone_web.entity.CustomerEntity;

import java.util.List;

public interface ICustomer {

    List<CustomerEntity> findByPhoneNumber(String phoneNumber);
}
