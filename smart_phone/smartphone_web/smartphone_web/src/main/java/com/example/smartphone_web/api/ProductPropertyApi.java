package com.example.smartphone_web.api;


import com.example.smartphone_web.dto.respone.order_detail.OrderDetailRespone;
import com.example.smartphone_web.service.IProductProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-property")
public class ProductPropertyApi {

    private final IProductProperty service;


    // Search keyword product => tạo hóa đơn
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        OrderDetailRespone respone = service.findById(id);
        return ResponseEntity.ok().body(respone);
    }
}
