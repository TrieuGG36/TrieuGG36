package com.example.smartphones.controller;

import com.example.smartphones.dto.respone.cart.CartRespone;
import com.example.smartphones.entity.CustomerEntity;
import com.example.smartphones.repository.OrderRepo;
import com.example.smartphones.service.ICart;
import com.example.smartphones.service.impl.CustomerService;
import com.example.smartphones.util.ConvertUtil;
import com.example.smartphones.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final ICart cartService;

    private final ConvertUtil convertUtil;

    private final SessionUtil sessionUtil;

    private final CustomerService customerService;
    private final OrderRepo ordersRepo;


    @GetMapping("")
    public String indexOder(Model model){
        List<CartRespone> list = cartService.findByCustomer();
        CustomerEntity entity = customerService.findByEmail(String.valueOf(sessionUtil.getObject("username")));

        long tong = 0;
        boolean check = true;
        for (CartRespone cart : list) {
            tong += cart.getTotal();
            if (cart.getPriceProductPromotion() > 0){
                check = false;
            }
        }
        model.addAttribute("promotion", check ? 1 : 0);
        model.addAttribute("listCart", list);
        model.addAttribute("tong", convertUtil.moneyToStringFormat(tong));
        model.addAttribute("tongOrder", convertUtil.moneyToStringFormat(tong + 30000));
        model.addAttribute("tongPrice", tong);
        model.addAttribute("cutomer", entity);
        return "views/checkout/checkout";
    }
}
