package com.example.assjava5.controller.Admin;

import com.example.assjava5.entity.Category;
import com.example.assjava5.entity.Product;
import com.example.assjava5.repositories.CategoryRepository;
import com.example.assjava5.repositories.ProductRepository;
import com.example.assjava5.utilities.uploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("admin/product")
public class ProductAdminController {
    @Autowired
    private uploadFile uploadFilea;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @ModelAttribute("category")
    public Iterable<Category> provinces(){
        return categoryRepository.findAll();
    }

    @GetMapping()
    public String index(Model model){
        Pageable pageable;
        String page = request.getParameter("page");
        long count =  productRepository.count() % 5 == 0 ? productRepository.count() / 5 - 1 : productRepository.count() / 5 ;
        model.addAttribute("max", count);
        if(page != null){
            pageable = PageRequest.of(Integer.parseInt(page), 5);
            model.addAttribute("page", page);
        }else {
            pageable = PageRequest.of(0, 5);
            model.addAttribute("page", 0);
        }
        List<Product> products = productRepository.getProductByActive(pageable);
        model.addAttribute("list", products);
        return "/views/admin/listProduct";
    }



    @GetMapping("/add")
    public String doGetAdd(){
        return "/views/admin/addProduct";
    }

    @GetMapping("/edit/{id}")
    public String doGetEdit(@PathVariable("id") Product product, Model model){
        System.out.println(product.getPhoto() + " Do get");
        model.addAttribute("product", product);
        return "/views/admin/editProduct";
    }


    @PostMapping("/edit/{id}")
    public String doPostEdit(Model model, @Validated Product product, BindingResult result,
                             @RequestParam("category1") Category category) throws IOException {
        if (result.hasErrors()) {
            //model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("product", product);
            System.out.println(result.getAllErrors());
            return "/views/admin/editProduct";
        } else {
            int count = 0;
            product.setCategory(category);
            productRepository.save(product);
            return "redirect:/admin/product";
        }
    }

    @PostMapping("/add")
    public String doPostAdd(@Validated Product product, BindingResult result, Model model, @RequestParam("photo1") MultipartFile uploadFile,
                            @RequestParam("category1") String idCategory) throws IOException {
        if(result.hasErrors()){
            System.out.println("loi");
            System.out.println(result.getAllErrors());
        }else {
            product.setRate(5);
            product.setActive(1);
            int count = 0;
            for (int i = uploadFile.getOriginalFilename().length() - 1; i >= 0 ; i--) {
                if(uploadFile.getOriginalFilename().charAt(i) == '.'){
                    count = i;
                    break;
                }
            }
            Category category = categoryRepository.getById(Integer.valueOf(idCategory));
            product.setPhoto("1");
            product.setCategory(category);
            Product product1 = productRepository.save(product);
            uploadFilea.handleUploadFile(uploadFile, product1.getId() + uploadFile.getOriginalFilename().substring(count), true);
            product1.setPhoto(product1.getId() + uploadFile.getOriginalFilename().substring(count));
            productRepository.save(product1);
        }
        return "redirect:/admin/product";
    }


    @GetMapping("/delete/{id}")
    public String doGetDelete(@PathVariable("id") Product product){
        product.setActive(0);
        productRepository.save(product);
        return "redirect:/admin/product";
    }
}
