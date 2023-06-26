package com.example.smartphone_web.controller;

import com.example.smartphone_web.dto.respone.order.OrderRespone;
import com.example.smartphone_web.service.IOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final IOrder orderService;
    @GetMapping("/orders")
    public String index(Model model){
        List<OrderRespone> respones = orderService.findAllOrder();
        model.addAttribute("list", respones);
        return "views/orders/007_Orders_payment";

    }
}
