package com.example.assjava5.controller;


import com.example.assjava5.constant.SessionArr;
import com.example.assjava5.entity.Cart;
import com.example.assjava5.entity.Customer;
import com.example.assjava5.entity.Order;
import com.example.assjava5.entity.Product;
import com.example.assjava5.repositories.CardRepository;
import com.example.assjava5.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CardController {
    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private OrderRepository repository;



    @GetMapping("card")
    public String cardIndex(Model model){
        Customer customer = (Customer) session.getAttribute(SessionArr.CURRENT_USER);
        List<Object[]> list = cardRepository.getCartAndProduct(customer.getId());
        int count = cardRepository.countByCustomer_Id(customer.getId());
        model.addAttribute("count", count);
        model.addAttribute("customer", customer);
        model.addAttribute("listObj", list);
        return "/views/user/cardProduct";
    }


    @GetMapping("card/delete/{id}")
    public  void doGetDeleteCard(@PathVariable("id")Cart cart){
            cardRepository.delete(cart);
    }


    @GetMapping("card/update/{id}")
    public  void doGetUpdateCard(@PathVariable("id")Cart cart){
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");
        cart.setQuantity(Integer.valueOf(quantity));
        cart.setPrice(Integer.valueOf(price));
        cardRepository.save(cart);
    }

    @GetMapping("card/add/{id}")
    public  void doGetUpdateCard(@PathVariable("id")Product product){
        int check = 0;
        Customer customer = (Customer) session.getAttribute(SessionArr.CURRENT_USER);
        List<Object[]> list = cardRepository.getCartAndProduct(customer.getId());
        for (int i = 0 ; i < list.size(); i ++){
            if(list.get(i)[0] == product.getId()){
                Cart cart = cardRepository.findById((Integer) list.get(i)[5]).get();
                if(cart.getQuantity() == product.getQuantity()){
                    check = 1;
                    break;
                }
                cart.setCustomer(customer);
                cart.setProduct(product);
                cart.setQuantity(cart.getQuantity() + 1);
                cart.setPrice(product.getPrice() * cart.getQuantity());
                cardRepository.save(cart);
                check = 1;
            }
        }
        if(check == 0){
            Cart cart = new Cart();
            cart.setCustomer(customer);
            cart.setProduct(product);
            cart.setQuantity(1);
            cart.setPrice(product.getPrice());
            cardRepository.save(cart);
        }
    }



}
