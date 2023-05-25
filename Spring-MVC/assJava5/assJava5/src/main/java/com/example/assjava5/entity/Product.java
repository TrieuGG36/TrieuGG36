package com.example.assjava5.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nameProduct;

    @Column(nullable = true, length = 255)
    private String photo;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String capacity;


    @Column(nullable = false)
    private Integer rate;

    @Column(nullable = true, length = 255)
    private Integer active;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;


    @OneToMany(mappedBy = "product")
    private List<Cart> carts;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;
}
