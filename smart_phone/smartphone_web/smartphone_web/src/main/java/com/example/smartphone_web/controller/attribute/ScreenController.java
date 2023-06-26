package com.example.smartphone_web.controller.attribute;

import com.example.smartphone_web.service.IScreen;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("screen")
public class ScreenController {

    private final IScreen screenService;

    @GetMapping
    public String index(Model model){

        model.addAttribute("list", screenService.findAllScreen());
//        model.addAttribute("listscreen", screenService1.findAll());
        return "/views/product/attribute/screen";
    }

}
