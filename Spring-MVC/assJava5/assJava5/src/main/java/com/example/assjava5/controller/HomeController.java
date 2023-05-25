package com.example.assjava5.controller;


import com.example.assjava5.entity.Category;
import com.example.assjava5.entity.Product;
import com.example.assjava5.repositories.CategoryRepository;
import com.example.assjava5.repositories.ProductRepository;
import com.example.assjava5.utilities.uploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@CrossOrigin("*")
public class HomeController {
    @Autowired
    private HttpServletRequest request;


    @Autowired
    private uploadFile uploadFilea;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("index")
    public String index(Model model){

        String search = request.getParameter("search");
        if(search != null){
            searchProduct(search, model);
        }else {
            loadProductIndex(model);
        }

        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("listCate", categoryList);
        Pageable pageable1 = PageRequest.of(0,8);
        List<Product> productList = productRepository.getProductByActive(pageable1);
        model.addAttribute("listProduct", productList);
        return "/views/user/index";
    }


    @GetMapping("index/all")
    public String allProduct(Model model){

        Pageable pageable;
        String min = request.getParameter("min");
        String max = request.getParameter("max");

        long count =  productRepository.count() % 6 == 0 ? productRepository.count() / 6 - 1 : productRepository.count() / 6 ;
        model.addAttribute("max", count);
        if(request.getParameter("page") != null){
            pageable = PageRequest.of(Integer.parseInt(request.getParameter("page")),6);
            model.addAttribute("page", request.getParameter("page"));
        }else {
            pageable = PageRequest.of(0,6);
            model.addAttribute("page",0);
        }
        if(min != null){
            model.addAttribute("min", min);
            List<Product> productMain = productRepository.findProductsByPrice(Integer.parseInt(min), Integer.parseInt(max), pageable);
            model.addAttribute("listProductMain", productMain);


        }else {
            List<Product> productMain = productRepository.getProductByActive(pageable);
            model.addAttribute("listProductMain", productMain);
        }



        model.addAttribute("dung", "gan");

        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("listCate", categoryList);

        Pageable pageable1 = PageRequest.of(0,8);
        Page productList = productRepository.findAll(pageable1);
        model.addAttribute("listProduct", productList);
        return "/views/user/index";
    }







    private void loadProductIndex(Model model){
        Pageable pageable = PageRequest.of(0,3);
        List<Product> productList1 = productRepository.getProductByCapacity(1, pageable);
        List<Product> productList2 = productRepository.getProductByCapacity(2, pageable);
        List<Product> productList3 = productRepository.getProductByCapacity(5, pageable);
        model.addAttribute("listProduct1", productList1);
        model.addAttribute("listProduct2", productList2);
        model.addAttribute("listProduct3", productList3);
        model.addAttribute("dung", "sai");
    }

    private void searchProduct(String name, Model model){
        List<Product> productListSearch = productRepository.getProductLikeName(name);
        System.out.println(productListSearch.size());
        model.addAttribute("listProductSearch", productListSearch);
        model.addAttribute("dung", "dung");
    }



}
