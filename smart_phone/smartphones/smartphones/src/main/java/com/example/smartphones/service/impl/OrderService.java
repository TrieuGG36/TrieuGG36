package com.example.smartphones.service.impl;


import com.example.smartphones.config.Config;
import com.example.smartphones.dto.request.orders.OrdersRequest;
import com.example.smartphones.dto.respone.VPResponDto;
import com.example.smartphones.dto.respone.cart.CartRespone;
import com.example.smartphones.dto.respone.order.OrderRespone;
import com.example.smartphones.entity.*;
import com.example.smartphones.repository.*;
import com.example.smartphones.service.IOrder;
import com.example.smartphones.util.ConvertUtil;
import com.example.smartphones.util.SessionUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderService implements IOrder {

    private final OrderRepo orderRepo;
    private final OrderDetailRepo orderDetailRepo;
    private final CartRepo cartRepo;
    private final CustomerRepo customerRepo;
    private final SessionUtil sessionUtil;
    private final ConvertUtil convertUtil;
    private final ColorService colorService;
    private final RomRepo romRepo;
    private final ProductRepo productRepo;
    private final ProductPropertyRepo productPropertyRepo;

    @Override
    public String createOrder(OrdersRequest request) {
        try {
            CustomerEntity customerEntity = customerRepo.findByEmail(String.valueOf(sessionUtil.getObject("username")));
            OrdersEntity ordersEntity = new OrdersEntity();
            /*if (request.getVoucherCode() != null){
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                VoucherEntity voucherEntity = voucherRepo.findByDeleteFlagIsFalseAndCode(date, request.getVoucherCode());
                ordersEntity.setVoucherEntity(voucherEntity);
            }*/
            ordersEntity.setCustomerEntity(customerEntity);
            ordersEntity.setAddress(request.getAddress());
            ordersEntity.setStatusPay(0);
            ordersEntity.setNote(request.getNote());
            ordersEntity.setReceiveDate(Timestamp.valueOf(LocalDateTime.now()));
            ordersEntity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
            ordersEntity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
            ordersEntity.setStatus("0");
            ordersEntity.setTypeOrder(1);
            ordersEntity.setTotalMoney(request.getTotalMoney());

            OrdersEntity ordersEntity1 = orderRepo.save(ordersEntity);
            ordersEntity1.setCodeOrder("HD000" + ordersEntity1.getId());
            orderRepo.save(ordersEntity1);
            List<CartEntity> cartEntities = cartRepo.findByDeleteFlagIsFalseAndIdCustomer(customerEntity.getId());
            for (CartEntity cart : cartEntities) {
                OrdersDetailEntity ordersDetailEntity = new OrdersDetailEntity();
                ordersDetailEntity.setOrdersEntity(ordersEntity);
                ordersDetailEntity.setQuantity(cart.getQuantity());
                ordersDetailEntity.setIdPropertyProduct(cart.getIdProduct().getId());
                ordersDetailEntity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
                ordersDetailEntity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
                ordersDetailEntity.setPrice(cart.getIdProduct().getPricePromotion() == 0 ? cart.getIdProduct().getPrice()
                        : cart.getIdProduct().getPricePromotion());
                orderDetailRepo.save(ordersDetailEntity);
                cartRepo.delete(cart);
            }
            return "ok";
        }catch (Exception e){
            return "false";
        }
    }

    @Override
    public VPResponDto createOrderOnline(OrdersRequest request, HttpServletRequest request1) {
        try {
            Gson gson = new Gson();
            System.out.println(convertUtil.moneyToStringFormat(request.getTotalMoney()));
            CustomerEntity customerEntity = customerRepo.findByEmail(String.valueOf(sessionUtil.getObject("username")));
            OrdersEntity ordersEntity = new OrdersEntity();
            /*if (request.getVoucherCode() != null){
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                VoucherEntity voucherEntity = voucherRepo.findByDeleteFlagIsFalseAndCode(date, request.getVoucherCode());
                ordersEntity.setVoucherEntity(voucherEntity);
            }*/
            ordersEntity.setCustomerEntity(customerEntity);
            ordersEntity.setAddress(request.getAddress());
            ordersEntity.setTypeOrder(1);
            ordersEntity.setStatusPay(1);
            ordersEntity.setNote(request.getNote());
            ordersEntity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
            ordersEntity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
            ordersEntity.setReceiveDate(Timestamp.valueOf(LocalDateTime.now()));
            ordersEntity.setStatus("-2");
            ordersEntity.setTotalMoney(request.getTotalMoney());
            OrdersEntity ordersEntity1 = orderRepo.save(ordersEntity);
            ordersEntity1.setCodeOrder("HD000" + ordersEntity1.getId());
            orderRepo.save(ordersEntity1);
            List<CartEntity> cartEntities = cartRepo.findByDeleteFlagIsFalseAndIdCustomer(customerEntity.getId());
            for (CartEntity cart : cartEntities) {
                OrdersDetailEntity ordersDetailEntity = new OrdersDetailEntity();
                ordersDetailEntity.setOrdersEntity(ordersEntity);
                ordersDetailEntity.setQuantity(cart.getQuantity());
                ordersDetailEntity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
                ordersDetailEntity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
                ordersDetailEntity.setIdPropertyProduct(cart.getIdProduct().getId());
                ordersDetailEntity.setPrice(cart.getIdProduct().getPricePromotion() == 0 ? cart.getIdProduct().getPrice()
                        : cart.getIdProduct().getPricePromotion());
                orderDetailRepo.save(ordersDetailEntity);
                cartRepo.delete(cart);
            }
            VPResponDto vpResponDto = gson.fromJson(VNPAYService.payments(ordersEntity1.getId(), Math.toIntExact(request.getTotalMoney()), Config.getRandomNumber(8), request1), VPResponDto.class);
            return vpResponDto;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<OrderRespone> findAllOrder() {
        CustomerEntity customerEntity = customerRepo.findByEmail(String.valueOf(sessionUtil.getObject("username")));
        List<OrdersEntity> entityList = orderRepo.findByCustomerEntityOrderByCreateDateDesc(customerEntity);
        List<OrderRespone> list = new ArrayList<>();
        for (OrdersEntity entity : entityList) {
            OrderRespone respone = new OrderRespone();
            respone.setId(String.valueOf(entity.getId()));
            respone.setCodeOrder(entity.getCodeOrder());
            respone.setCreateDate(String.valueOf(entity.getCreateDate()));
            respone.setStatus(entity.getStatus());
            respone.setTotalString(convertUtil.moneyToStringFormat(entity.getTotalMoney()));
            list.add(respone);
        }
        return list;
    }

    @Override
    public List<CartRespone> findByOrderDetail(String id) {
        OrdersEntity entity = orderRepo.findByCodeOrder(id);
        List<OrdersDetailEntity> list = orderDetailRepo.findByDeleteFlagIsFalseAndOrdersEntity(entity.getId());
        List<CartRespone> list1 = new ArrayList<>();
        for (OrdersDetailEntity detail: list
        ) {
            CartRespone respone = mapToRespone(detail);
            list1.add(respone);
        }
        return list1;
    }

    @Override
    public OrdersEntity findByOrder(String id) {
        OrdersEntity entity = orderRepo.findByCodeOrder(id);
        return entity;
    }

    @Override
    public String canncelOrder(String id) {
        OrdersEntity entity = orderRepo.findByCodeOrder(id);
        if (Integer.parseInt(entity.getStatus()) > 1){
            for (OrdersDetailEntity detail: entity.getOrdersDetailEntities()) {
                ProductPropertyEntity propertyEntity = productPropertyRepo.getById(detail.getIdPropertyProduct());
                propertyEntity.setQuantity(detail.getQuantity() + propertyEntity.getQuantity());
                productPropertyRepo.save(propertyEntity);
                /*List<ImeiEntity> imeiEntityList = imeiRepo.findByDeleteFlagIsFalseAndPropertyProductIdAndOrderDetailId(propertyEntity.getId(), detail.getId());
                for (ImeiEntity imei : imeiEntityList){
                    imei.setStatus(StatusImei.CHUA_BAN.getValue());
                    imei.setOrderDetailId(null);
                    imeiRepo.save(imei);
                }*/
            }
        }else if(Integer.parseInt(entity.getStatus()) == 1){
            for (OrdersDetailEntity detail : entity.getOrdersDetailEntities()){

                /*List<ImeiEntity> imeiEntityList = imeiRepo.findByDeleteFlagIsFalseAndPropertyProductIdAndOrderDetailId(detail.getIdPropertyProduct(), detail.getId());
                for (ImeiEntity imei : imeiEntityList){
                    imei.setStatus(StatusImei.CHUA_BAN.getValue());
                    imei.setOrderDetailId(null);
                    imeiRepo.save(imei);
                }*/
            }
        }
        entity.setStatus("-1");
        orderRepo.save(entity);
        return "ok";
    }

    private CartRespone mapToRespone(OrdersDetailEntity entity){
        CartRespone respone = new CartRespone();
        ProductPropertyEntity propertyEntity = productPropertyRepo.findByDeleteFlagIsFalseAndId(entity.getIdPropertyProduct());
        respone.setId(String.valueOf(entity.getId()));
        respone.setColor(colorService.findById(propertyEntity.getColorId()));
        RomEntity romEntity = romRepo.findById(propertyEntity.getRomId()).get();
        ProductEntity productEntity = productRepo.findById(romEntity.getProductId()).get();
        respone.setImgProduct(productEntity.getImage_key());
        respone.setIdProduct(String.valueOf(productEntity.getId()));
        respone.setQuantityProduct(propertyEntity.getQuantity());
        respone.setNameProduct(productEntity.getName());
        respone.setQuantity(entity.getQuantity());
        respone.setPriceProduct(entity.getPrice());
        respone.setPriceProductString(convertUtil.moneyToStringFormat(entity.getPrice()));
        respone.setRom(romEntity.getName() + " GB");
        respone.setPriceProductPromotion(0);
        respone.setPriceProductPromotionString(convertUtil.moneyToStringFormat(0L));
        if(respone.getPriceProductPromotion() > 0){
            respone.setTotal(respone.getQuantity() * respone.getPriceProductPromotion());
            respone.setTotalString(convertUtil.moneyToStringFormat(respone.getTotal()));
        }else {
            respone.setTotal(respone.getQuantity() * respone.getPriceProduct());
            respone.setTotalString(convertUtil.moneyToStringFormat(respone.getTotal()));
        }
        return respone;
    }
}
