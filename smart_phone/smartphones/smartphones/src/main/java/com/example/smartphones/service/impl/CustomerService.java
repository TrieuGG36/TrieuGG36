package com.example.smartphones.service.impl;

import com.example.smartphones.dto.request.customer.CustomerRequest;
import com.example.smartphones.entity.CustomerEntity;
import com.example.smartphones.repository.CustomerRepo;
import com.example.smartphones.service.ICustomer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService implements ICustomer {

    private final CustomerRepo repo;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public CustomerEntity findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public String addCustomer(CustomerRequest request) {
        try {
            CustomerEntity entity = new CustomerEntity();
            entity.setEmail(request.getEmail());
            entity.setFullName(request.getName());
            entity.setPhoneNumber(request.getPhone());

            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(request.getDate());
            entity.setDateOfBirth(date1);
            entity.setPassWord(bCryptPasswordEncoder.encode(request.getPassword()));
            repo.save(entity);
        }catch (Exception e){
            return "false";
        }
        return "ok";
    }

    @Override
    public CustomerEntity findByPhone(String phone) {
        return repo.findByPhoneNumber(phone);
    }
}
