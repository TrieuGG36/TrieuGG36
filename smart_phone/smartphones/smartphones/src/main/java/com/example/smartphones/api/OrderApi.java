package com.example.smartphones.api;

import com.example.smartphones.dto.request.orders.OrdersRequest;
import com.example.smartphones.dto.respone.VPResponDto;
import com.example.smartphones.service.impl.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderApi {

    private final OrderService orderService;


    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrdersRequest request){
        String check = orderService.createOrder(request);
        if (check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }else{
            return ResponseEntity.badRequest().body("false");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable String id){
        String check = orderService.canncelOrder(id);
        if (check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }else {
            return ResponseEntity.badRequest().body("false");
        }
    }

    @PostMapping("/create/online")
    public ResponseEntity<?> createOnlineOrder(@RequestBody OrdersRequest request, HttpServletRequest request1){
        VPResponDto check = orderService.createOrderOnline(request, request1);
        if (check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }else{
            return ResponseEntity.badRequest().body("false");
        }
    }
}
