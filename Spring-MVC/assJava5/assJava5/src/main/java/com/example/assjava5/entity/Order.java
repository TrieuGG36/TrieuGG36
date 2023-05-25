package com.example.assjava5.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Timestamp createDate;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer total;


    @Column(nullable = false)
    private Integer statusMoney;

    @Column(nullable = false)
    private Integer statusShip;

    @Column(nullable = false)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
