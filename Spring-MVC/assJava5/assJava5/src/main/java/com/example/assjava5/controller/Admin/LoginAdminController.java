package com.example.assjava5.controller.Admin;


import com.example.assjava5.constant.SessionArr;
import com.example.assjava5.entity.User;
import com.example.assjava5.repositories.UserRepository;
import com.example.assjava5.utilities.HashUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginAdminController {
    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/login")
    public String login(){
        return "/views/admin/login";
    }

    @GetMapping("/admin/logout")
    public String logout(){
        session = request.getSession();
        session.removeAttribute(SessionArr.CURRENT_USERAdmin);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String doPostlogin(@RequestParam("email") String email, @RequestParam("password") String pass, Model model){
        session = request.getSession();
        User user = userRepository.findByEmail(email);
        if(user == null){
            model.addAttribute("mess", "Sai email hoặc password");
            return "/views/admin/login";
        }
        boolean checkPwd = HashUntil.verify(pass, user.getPass());
        if(checkPwd){
            session.setAttribute(SessionArr.CURRENT_USERAdmin, user);
            return "redirect:/admin";
        }else {
            model.addAttribute("mess", "Sai email hoặc password");
            return "/views/admin/login";
        }

    }

    @GetMapping("/forgot")
    public String forgot(){

        return "/views/admin/forgotPass";
    }

}
