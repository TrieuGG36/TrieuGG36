package com.example.assjava5.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fullname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;


    @Column(nullable = false)
    private Integer point;

    @Column(nullable = true)
    private String pass;

    @OneToMany(mappedBy = "customer")
    private List<Cart> carts;

    @OneToMany(mappedBy = "customer")
    private List<Comment> comments;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

}
