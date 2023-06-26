package com.example.smartphones.service;

import com.example.smartphones.dto.request.orders.OrdersRequest;
import com.example.smartphones.dto.respone.VPResponDto;
import com.example.smartphones.dto.respone.cart.CartRespone;
import com.example.smartphones.dto.respone.order.OrderRespone;
import com.example.smartphones.entity.OrdersEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IOrder {

    String createOrder(OrdersRequest request);

    VPResponDto createOrderOnline(OrdersRequest request, HttpServletRequest request1);

    List<OrderRespone> findAllOrder();

    List<CartRespone> findByOrderDetail(String id);

    OrdersEntity findByOrder(String id);

    String canncelOrder(String id);
}
