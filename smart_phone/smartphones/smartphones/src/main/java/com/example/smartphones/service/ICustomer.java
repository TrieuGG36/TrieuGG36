package com.example.smartphones.service;


import com.example.smartphones.dto.request.customer.CustomerRequest;
import com.example.smartphones.entity.CustomerEntity;

import java.text.ParseException;


public interface ICustomer {
    CustomerEntity findByEmail(String email);

    String addCustomer(CustomerRequest request);

    CustomerEntity findByPhone(String phone);
}
