package com.example.assjava5.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "categorys")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nameCategory;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
