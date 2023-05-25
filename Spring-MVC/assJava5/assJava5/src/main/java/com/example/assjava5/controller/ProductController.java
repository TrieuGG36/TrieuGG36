package com.example.assjava5.controller;


import com.example.assjava5.constant.SessionArr;
import com.example.assjava5.entity.Category;
import com.example.assjava5.entity.Comment;
import com.example.assjava5.entity.Customer;
import com.example.assjava5.entity.Product;
import com.example.assjava5.repositories.CategoryRepository;
import com.example.assjava5.repositories.CommentRepository;
import com.example.assjava5.repositories.OrderDetilsRepository;
import com.example.assjava5.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetilsRepository orderDetilsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/product/{id}")
    public String showOneProduct(Model model, @PathVariable("id")Product product){
        List<Comment> commentList = commentRepository.findByProduct(product.getId());
        int countComment = commentRepository.countCommentByProduct(product.getId());
        model.addAttribute("commentList", commentList);
        model.addAttribute("countComment", countComment);
        session = request.getSession();
        Customer customer = (Customer) session.getAttribute(SessionArr.CURRENT_USER);
        if(customer != null){
            boolean check = false;
            List<Product> productListByOder = orderDetilsRepository.getProductByCustomers(customer.getId());
            for (int i = 0; i < productListByOder.size(); i++) {
                if(productListByOder.get(i).getId() == product.getId()){
                    check = true;
                    if(commentRepository.countByCustomer_IdAndProduct_Id(customer.getId(), product.getId()) == 1){
                        check = false;
                    }
                }
            }


            model.addAttribute("check", check);
        }

        Pageable pageable1 = PageRequest.of(0,8);
        Page productList = productRepository.findAll(pageable1);
        model.addAttribute("listProduct", productList);
        model.addAttribute("productMain", product);

        return "/views/user/showProduct";
    }

    @GetMapping("/category/{id}")
    public String showByCategory(Model model, @PathVariable("id") Category category){
        Pageable pageable1;
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        if(request.getParameter("page") != null){
            pageable1 = PageRequest.of(Integer.parseInt(request.getParameter("page")),6);
            model.addAttribute("page", request.getParameter("page"));
        }else {
            pageable1 = PageRequest.of(0,6);
            model.addAttribute("page",0);
        }

        if(min != null){
            model.addAttribute("min", min);
            List<Product> list = productRepository.findProductsByPriceaAndCategory(category.getId(), Integer.parseInt(min), Integer.parseInt(max), pageable1);
            model.addAttribute("listProductByCategory", list);
        }else {
            List<Product> list = productRepository.getProductByCategory(category.getId(), pageable1);
            model.addAttribute("listProductByCategory", list);
        }




        showPhu(model);
        model.addAttribute("id", category.getId());

        model.addAttribute("dung", "gan");
        return "/views/user/allproduct";
    }


    private void showPhu(Model model){
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("listCate", categoryList);
        Pageable pageable1 = PageRequest.of(0,8);
        Page productList = productRepository.findAll(pageable1);
        model.addAttribute("listProduct", productList);
    }
}
