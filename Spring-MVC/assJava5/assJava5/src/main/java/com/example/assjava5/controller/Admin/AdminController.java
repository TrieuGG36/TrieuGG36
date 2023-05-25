package com.example.assjava5.controller.Admin;


import com.example.assjava5.entity.User;
import com.example.assjava5.repositories.UserRepository;
import com.example.assjava5.utilities.HashUntil;
import com.example.assjava5.utilities.uploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private uploadFile uploadFilea;

    @GetMapping()
    public String index(Model model){
        Pageable pageable;
        String page = request.getParameter("page");
        long count =  userRepository.count() % 5 == 0 ? userRepository.count() / 5 - 1 : userRepository.count() / 5 ;
        model.addAttribute("max", count);
        if(page != null){
            pageable = PageRequest.of(Integer.parseInt(page), 5);
            model.addAttribute("page", page);
        }else {
            pageable = PageRequest.of(0, 5);
            model.addAttribute("page", 0);
        }
        Page list = userRepository.findAll(pageable);
        model.addAttribute("listUser", list);
        return "/views/admin/home";
    }


    @GetMapping("/user")
    public String create(@RequestParam("action") String action, Model model){
        if(action.equals("add")){
            User user = null;
            model.addAttribute("user", user);
            model.addAttribute("isEdit", false);
            return "/views/admin/editUser";
        }else {
            String id = request.getParameter("id");
            User user = userRepository.getById(Integer.parseInt(id));
            model.addAttribute("user", user);
            model.addAttribute("isEdit", true);
            model.addAttribute("link" , "/admin/user?action=edit&id=" +user.getId());
        }
        return "/views/admin/editUser";
    }


    @PostMapping("/user")
    public String doPostAdd(@RequestParam("action") String action, User user, @RequestParam("photo1") MultipartFile uploadFile) throws IOException {
        if(action.equals("add")){
           addUser(user, uploadFile);
            return "redirect:/admin";
        }else {
            String id = request.getParameter("id");
            User user1 = userRepository.getById(Integer.valueOf(id));
            int count = 0;
            if (uploadFile.getOriginalFilename().trim().length() != 0){

                for (int i = uploadFile.getOriginalFilename().length() - 1; i >= 0 ; i--) {
                    if(uploadFile.getOriginalFilename().charAt(i) == '.'){
                        count = i;
                        break;
                    }
                }
                uploadFilea.handleUploadFile(uploadFile, user1.getId() + uploadFile.getOriginalFilename().substring(count), false);
                user1.setPhoto(user1.getId() + uploadFile.getOriginalFilename().substring(count));
            }

            user.setPhoto(user1.getPhoto());
            user.setPass(user1.getPass());
            userRepository.save(user);

        }
        return "redirect:/admin";
    }

    @PostMapping("/user/delete/{id}")
    public String doPostDelete(@PathVariable("id") User user){
        userRepository.delete(user);
        return "redirect:/admin";
    }

    private void addUser(User user, MultipartFile uploadFile) throws IOException {
        int count = 0;
        for (int i = uploadFile.getOriginalFilename().length() - 1; i >= 0 ; i--) {
            if(uploadFile.getOriginalFilename().charAt(i) == '.'){
                count = i;
                break;
            }
        }
        user.setPhoto("1");
        User user1 = userRepository.save(user);
        uploadFilea.handleUploadFile(uploadFile, user1.getId() + uploadFile.getOriginalFilename().substring(count), false);
        user1.setPhoto(user1.getId() + uploadFile.getOriginalFilename().substring(count));
        String hashedPassword = HashUntil.hash(user1.getPass());
        user1.setPass(hashedPassword);
        userRepository.save(user1);
    }

}
