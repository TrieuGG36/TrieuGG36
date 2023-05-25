package com.example.assjava5.controller;


import com.example.assjava5.constant.SessionArr;
import com.example.assjava5.entity.Comment;
import com.example.assjava5.entity.Customer;
import com.example.assjava5.entity.Product;
import com.example.assjava5.repositories.CommentRepository;
import com.example.assjava5.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductRepository productRepository;

//        @GetMapping("/addcmt/{id}")
//        public void addComment(@PathVariable("id")Product product , @Validated Comment comment){
//            session = request.getSession();
//            Customer customer = (Customer) session.getAttribute(SessionArr.CURRENT_USER);
//            comment.setCustomer(customer);
//            comment.setProduct(product);
//            comment.setCreateDate(new Timestamp(System.currentTimeMillis()));
//            commentRepository.save(comment);
//            List<Comment> commentList = commentRepository.findByProduct(product.getId());
//            int rate = 0;
//            for (int i = 0; i < commentList.size(); i++) {
//                rate+=commentList.get(i).getRate();
//            }
//            rate = rate / commentList.size();
//            product.setRate(rate);
//            productRepository.save(product);
//
//
//        }


            @GetMapping("/addcmt/{id}")
            @ResponseBody
            public ResponseEntity<?> addNewComment(@PathVariable("id")Product product , @Validated Comment comment){
            session = request.getSession();
            Customer customer = (Customer) session.getAttribute(SessionArr.CURRENT_USER);
            comment.setCustomer(customer);
            comment.setProduct(product);
            comment.setCreateDate(new Timestamp(System.currentTimeMillis()));
            commentRepository.save(comment);
            List<Comment> commentList = commentRepository.findByProduct(product.getId());
            int rate = 0;
            for (int i = 0; i < commentList.size(); i++) {
                rate+=commentList.get(i).getRate();
            }
            rate = rate / commentList.size();
            product.setRate(rate);
            productRepository.save(product);
            return ResponseEntity.ok(comment);
        }
}
