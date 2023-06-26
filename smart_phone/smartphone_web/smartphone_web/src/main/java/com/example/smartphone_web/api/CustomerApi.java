package com.example.smartphone_web.api;

import com.example.smartphone_web.entity.CustomerEntity;
import com.example.smartphone_web.service.ICustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer/search")
public class CustomerApi {

    private final ICustomer customer;


    @GetMapping("{phoneNumber}")
    public ResponseEntity<?> findByCustomer(@PathVariable("phoneNumber") String phoneNumber){
        List<CustomerEntity> response = customer.findByPhoneNumber(phoneNumber);

        return ResponseEntity.ok().body(response);
    }
}
