package com.example.assjava5.entity;


import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fullname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String pass;

    @Column(nullable = false)
    private Integer role;

    @ColumnDefault("1")
    @Column(nullable = false)
    private Integer active;

    @Column(nullable = true, length = 255)
    private String photo;

}
