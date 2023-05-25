package com.example.assjava5.controller.Admin;


import com.example.assjava5.entity.Order;
import com.example.assjava5.entity.OrderDetail;
import com.example.assjava5.entity.Product;
import com.example.assjava5.repositories.OrderRepository;
import com.example.assjava5.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BillController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ProductRepository productRepository;



    @GetMapping("/admin/bill")
    public String ChoXacNhan(Model model){
        Pageable pageable;
        String page = request.getParameter("page");
        long count =  orderRepository.count() % 5 == 0 ? orderRepository.count() / 5 - 1 : orderRepository.count() / 5 ;
        model.addAttribute("max", count);
        if(page != null){
            pageable = PageRequest.of(Integer.parseInt(page), 5);
            model.addAttribute("page", page);
        }else {
            pageable = PageRequest.of(0, 5);
            model.addAttribute("page", 0);
        }
        List<Order> list = orderRepository.findAllByStatus(pageable);
        model.addAttribute("listOrder", list);
        model.addAttribute("active", "bill");
        return "/views/admin/listBill";
    }


    @GetMapping("/admin/bill/ship")
    public String DangGiao(Model model){
        Pageable pageable;
        String page = request.getParameter("page");
        long count =  orderRepository.count() % 5 == 0 ? orderRepository.count() / 5 - 1 : orderRepository.count() / 5 ;
        model.addAttribute("max", count);
        if(page != null){
            pageable = PageRequest.of(Integer.parseInt(page), 5);
            model.addAttribute("page", page);
        }else {
            pageable = PageRequest.of(0, 5);
            model.addAttribute("page", 0);
        }
        List<Order> list = orderRepository.findAllByStatusShip(pageable);
        model.addAttribute("listOrder", list);
        model.addAttribute("active", "ship");
        return "/views/admin/listBill";
    }


    @GetMapping("/admin/bill/done")
    public String DoneShip(Model model){
        Pageable pageable;
        String page = request.getParameter("page");
        long count =  orderRepository.count() % 5 == 0 ? orderRepository.count() / 5 - 1 : orderRepository.count() / 5 ;
        model.addAttribute("max", count);
        if(page != null){
            pageable = PageRequest.of(Integer.parseInt(page), 5);
            model.addAttribute("page", page);
        }else {
            pageable = PageRequest.of(0, 5);
            model.addAttribute("page", 0);
        }
        List<Order> list = orderRepository.findAllByDoneShip(pageable);
        model.addAttribute("listOrder", list);
        model.addAttribute("done", "done");
        model.addAttribute("active", "done");
        return "/views/admin/listBill";
    }

    @GetMapping("/admin/bill/delete/{id}")
    public String deleteOrder(@PathVariable("id") Order order){
        for (OrderDetail detail: order.getOrderDetails()
             ) {
            Product product = detail.getProduct();
            Product entity = productRepository.findById(product.getId()).get();
            product.setQuantity(product.getQuantity() + detail.getQuantity());
            productRepository.save(entity);
        }
        order.setStatus(0);
        orderRepository.save(order);
        return "redirect:/admin/bill";
    }


    @GetMapping("/admin/confirm/{id}")
    public void confirmShip(@PathVariable("id") Order order){
        if(order.getStatusShip() == 1){
            order.setStatusMoney(1);
            order.setStatusShip(2);
        }
        if(order.getStatusShip() == 0){
            order.setStatusShip(1);
        }
        orderRepository.save(order);
    }
}
