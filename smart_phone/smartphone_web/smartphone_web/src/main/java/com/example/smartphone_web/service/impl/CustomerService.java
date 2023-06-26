package com.example.smartphone_web.service.impl;

import com.example.smartphone_web.entity.CustomerEntity;
import com.example.smartphone_web.repository.DAO.CustomerDAO;
import com.example.smartphone_web.service.ICustomer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService implements ICustomer {

    private final CustomerDAO dao;


    @Override
    public List<CustomerEntity> findByPhoneNumber(String phoneNumber) {
        return dao.findByPhoneNumber(phoneNumber);
    }
}
