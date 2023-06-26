package com.example.smartphone_web.api;

import com.example.smartphone_web.constant.ConstansErrorCode;
import com.example.smartphone_web.dto.request.product.ProductRequestAdd;
import com.example.smartphone_web.dto.request.product.ProductRequestEdit;
import com.example.smartphone_web.dto.respone.product.ProductResponse;
import com.example.smartphone_web.exception.SmartPhoneExp;
import com.example.smartphone_web.service.IProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductApi {

    private final IProduct productService;

    private final HttpServletRequest request;



    @PostMapping()
    public ResponseEntity<?> add(@RequestBody ProductRequestAdd productRequestAdd){
        String check = productService.createProduct(productRequestAdd);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body(String.valueOf(new SmartPhoneExp(ConstansErrorCode.PRODUCT_ERROR_CREATE).getErrorMessage().getVn()));
    }

    @PutMapping()
    public ResponseEntity<?> editProduct(@RequestBody ProductRequestEdit productRequestEdit){
        String check = productService.editProduct(productRequestEdit);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body(String.valueOf(new SmartPhoneExp(ConstansErrorCode.PRODUCT_ERROR_CREATE).getErrorMessage().getVn()));
    }

    @PutMapping("/status/{id}/{check}")
    public ResponseEntity<?> editProductStatus(@PathVariable("id") Long id, @PathVariable("check") String valude){
        String check;
        if(valude.equals("OFF")){
            check = productService.editStatusProduct(id, "OFF");
        }else {
            check = productService.editStatusProduct(id, "ON");
        }
        if(check.equals("ok")){
            return ResponseEntity.ok().body(check);
        }
        return ResponseEntity.badRequest().body(check);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        String check = productService.deleteProduct(id);
        if(check.equals("ok")){
            return ResponseEntity.ok().body(check);
        }
        return ResponseEntity.badRequest().body(check);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<ProductResponse>> searchProductByKeyword(@PathVariable("keyword") String keyword) {
        List<ProductResponse> productResponse = productService.getName(keyword);
        if (Objects.nonNull(productResponse)) {
            return ResponseEntity.ok(productResponse);
        }
        return ResponseEntity.badRequest().body(new ArrayList<>());
    }


    @GetMapping("/search1/{keyword}")
    public ResponseEntity<List<ProductResponse>> searchProductByKeywordNhapHang(@PathVariable("keyword") String keyword) {
        List<ProductResponse> productResponse = productService.getNameNhapHang(keyword);
        if (Objects.nonNull(productResponse)) {
            return ResponseEntity.ok(productResponse);
        }
        return ResponseEntity.badRequest().body(new ArrayList<>());
    }
}
