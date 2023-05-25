package com.example.assjava5.controller;


import com.example.assjava5.constant.SessionArr;
import com.example.assjava5.entity.*;
import com.example.assjava5.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetilsRepository orderDetilsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HttpSession session;


    @PostMapping("order")
    public void createOrder(@Validated Order order){

       Customer customer = (Customer) session.getAttribute(SessionArr.CURRENT_USER);
       order.setCustomer(customer);
       order.setCreateDate(new Timestamp(System.currentTimeMillis()));
       List<Cart> list = cardRepository.getCard(customer.getId());
       int tong = 0;
        for (int i = 0; i < list.size(); i++) {
            tong+=list.get(i).getPrice();
        }
        order.setTotal(tong);
        order.setStatus(1);
        Order order1 =   orderRepository.save(order);

        for (int i = 0; i < list.size(); i++) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order1);
            orderDetail.setPrice(list.get(i).getPrice());
            orderDetail.setQuantity(list.get(i).getQuantity());
            orderDetail.setProduct(list.get(i).getProduct());
            orderDetilsRepository.save(orderDetail);
            Product product = list.get(i).getProduct();
            product.setQuantity(product.getQuantity() - list.get(i).getQuantity());
            productRepository.save(product);
        }
        cardRepository.deleteAll();


    }

}
