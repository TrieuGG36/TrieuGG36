package com.example.smartphone_web.service.impl;

import com.example.smartphone_web.entity.OrdersDetailEntity;
import com.example.smartphone_web.entity.ProductPropertyEntity;
import com.example.smartphone_web.repository.OrdersDetailRepo;
import com.example.smartphone_web.repository.OrdersRepo;
import com.example.smartphone_web.repository.PropertyProductRepo;
import com.example.smartphone_web.service.IOrderDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetail {
    private final OrdersDetailRepo detailRepo;

    private final OrdersRepo ordersRepo;

    private  final PropertyProductRepo propertyProductRepo;
    @Override
    public String deleteDetail(Long id, String codeOrder) {
        OrdersDetailEntity detailEntity = detailRepo.findByDeleteFlagIsFalseAndId(id);
        detailEntity.setDeleteFlag(true);
        ProductPropertyEntity entity = propertyProductRepo.findById(detailEntity.getIdPropertyProduct()).get();
        entity.setQuantity(detailEntity.getQuantity() + entity.getQuantity());
        propertyProductRepo.save(entity);
        detailRepo.save(detailEntity);
        return "ok";
    }
}
