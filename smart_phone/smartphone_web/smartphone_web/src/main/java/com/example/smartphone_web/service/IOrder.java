package com.example.smartphone_web.service;

import com.example.smartphone_web.dto.request.order.OrderRequest;
import com.example.smartphone_web.dto.respone.order.OrderRespone;

import java.util.List;

public interface IOrder {

    List<OrderRespone> findAllOrder();

    OrderRespone findByOrder(String id);


    String addOrder(OrderRequest request);

    String updateOrder(OrderRequest request);

    String confirmOrder(OrderRequest request);

    String shipPingOrder(OrderRequest request);
    String donOrder(String id);
    String deleteOrder(String id);

    String exportOrder(OrderRequest request);
}
