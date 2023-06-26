package com.example.smartphone_web.api;

import com.example.smartphone_web.dto.respone.ThongKeDto;
import com.example.smartphone_web.repository.ThongKeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class ThongKeApi {

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private ThongKeRepo thongKeRepo;

    @GetMapping("/top-product-sale/{month}/{year}")
    public List<ThongKeDto> thongKe(@PathVariable("month") Integer month, @PathVariable("year") Integer year){
        return thongKeRepo.findStockAkhirPerProductIn(month, year);
    }
}
