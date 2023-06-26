package com.example.smartphone_web.controller.attribute;

import com.example.smartphone_web.service.ICam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("cam")
public class CamController {
    private final ICam service;

    @GetMapping
    public String index(Model model){
        model.addAttribute("list", service.findAll());
        return "/views/product/attribute/cam";
    }
}
