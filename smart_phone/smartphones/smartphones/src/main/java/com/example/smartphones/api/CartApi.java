package com.example.smartphones.api;

import com.example.smartphones.dto.request.cart.CartEditRequest;
import com.example.smartphones.dto.request.cart.CartRequest;
import com.example.smartphones.service.ICart;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/cart")
@RestController
@RequiredArgsConstructor
public class CartApi {

    private final ICart cartService;


    @PostMapping("")
    public ResponseEntity<?> addCart(@RequestBody CartRequest request){
        String check = cartService.addCart(request);
        if (check.equals("ok")){
            return ResponseEntity.ok().body("OK");
        }
        if (check.equals("false")){
            return ResponseEntity.badRequest().body("false");
        }else{
            System.out.println("11111111111111111111111111");
            return ResponseEntity.badRequest().body(check);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> editCart(@RequestBody List<CartEditRequest> request){
        String check = cartService.updateCart(request);
        if(check.equals("false")){
            return ResponseEntity.badRequest().body("false");
        }
        return ResponseEntity.ok().body(check);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        String check = cartService.deleteCart(id);
        if(check.equals("false")){
            return ResponseEntity.badRequest().body("false");
        }
        return ResponseEntity.ok().body("ok");

    }

}
