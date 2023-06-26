package com.example.smartphones.controller;

import com.example.smartphones.dto.respone.product.NewProductRespone;
import com.example.smartphones.dto.respone.product.ProductRespone;
import com.example.smartphones.service.IProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final IProduct service;


    @GetMapping("{id}")
    public String getOneProduct(Model model, @PathVariable("id") String id) {
        List<NewProductRespone> listRandom = service.findbyRandom();

        ProductRespone respone = service.findById(Long.valueOf(id));
        if (null == respone) {
            return "redirect:/home";
        }
        model.addAttribute("product", respone);
        model.addAttribute("listRandom", listRandom);
        return "/views/product-detail";
    }
}
