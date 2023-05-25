package com.example.assjava5.controller;


import com.example.assjava5.constant.SessionArr;
import com.example.assjava5.entity.Customer;
import com.example.assjava5.repositories.CustomerRepository;
import com.example.assjava5.utilities.HashUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CustomerRepository customerRepository;



    @PostMapping("registration")
    public String registration(Model model, @Validated Customer customer){
        customer.setPoint(1);
        String hashedPassword = HashUntil.hash(customer.getPass());
        customer.setPass(hashedPassword);
        customerRepository.save(customer);
        return "redirect:/index";
    }


    @PostMapping("login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String pass){
        HttpSession session = request.getSession();
        Customer customer = customerRepository.findByEmail(email);
        boolean checkPwd = HashUntil.verify(pass, customer.getPass());
        if(checkPwd){
            session.setAttribute(SessionArr.CURRENT_USER, customer);
            System.out.println("thanh cong");
        }else {
            System.out.println("that bai");
            return "redirect:/index";
        }
        return "redirect:/index";
    }

    @PostMapping("logout")
    public String logout() throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionArr.CURRENT_USER);
        return "redirect:/index";
    }
}
