package com.example.smartphone_web.controller;

import com.example.smartphone_web.dto.respone.ThongKeDto;
import com.example.smartphone_web.repository.ThongKeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ThongKeRepo thongKeRepo;

    @GetMapping("")
    public String index(Model model){
        try {
            List<ThongKeDto> thongKeDtos = thongKeRepo.findStockAkhirPerProductIn(12, 2022);
            return "/views/index";
        }catch (Exception e){
            e.printStackTrace();
            return "/views/index";
        }
    }
}
