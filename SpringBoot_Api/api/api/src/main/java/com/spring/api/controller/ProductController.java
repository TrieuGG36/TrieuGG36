package com.spring.api.controller;


import com.spring.api.model.Product;
import com.spring.api.model.ResponseObject;
import com.spring.api.reposirory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
// localhost:8080/api/v1/product
public class ProductController {

    // DI = Dependency Injection
    @Autowired
    private ProductRepository repository;


    @GetMapping("")
    List<Product> getAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<Product> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Đã tìm thấy id của product ",foundProduct)):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Không tìm thấy id là = " + id, ""));

//        if (foundProduct.isPresent()){
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "Tìm thấy product", foundProduct)
//            );
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("false", "Không tìm thấy id là = " + id, "")
//            );
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
        List<Product> foundNameProduct = repository.findByproductName(newProduct.getProductName().trim());
        if (foundNameProduct.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed","Tên Product đã tồn tại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Thêm dữ Product thành công", repository.save(newProduct)));
    }


    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id){
        Product updateProduct = repository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setYear(newProduct.getYear());
                    product.setPrice(newProduct.getPrice());
                    product.setUrl(newProduct.getUrl());
                    return repository.save(product);
                }).orElseGet(() ->{
                   newProduct.setId(id);
                   return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Cập nhật product thành công", updateProduct)
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> delete(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if (exists) {
            repository.deleteById(id);
             return ResponseEntity.status(HttpStatus.OK).body(
                     new ResponseObject("ok", "Xóa thành công", "")
             );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Không tìm thấy id để xóa", "")
        );
    }
}
