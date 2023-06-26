package com.example.smartphones.service;

import com.example.smartphones.dto.request.cart.CartEditRequest;
import com.example.smartphones.dto.request.cart.CartRequest;
import com.example.smartphones.dto.respone.cart.CartRespone;

import java.util.List;

public interface ICart {

    List<CartRespone> findByCustomer();

    String addCart(CartRequest cartRequest);


    String deleteCart(Long id);


    String updateCart(List<CartEditRequest> list);
}
