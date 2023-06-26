package com.example.smartphones.service.impl;

import com.example.smartphones.dto.respone.product.ProductPropertyRespone;
import com.example.smartphones.entity.ProductPropertyEntity;
import com.example.smartphones.repository.ProductPropertyRepo;
import com.example.smartphones.service.IProductProperty;
import com.example.smartphones.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductPropertyService implements IProductProperty {

    private final ProductPropertyRepo repo;
    private final ConvertUtil util;

    private final ColorService colorService;
    @Override
    public List<ProductPropertyRespone> findByRomId(Long romId) {
        List<ProductPropertyEntity> entityList = repo.findByRomId(romId);
        List<ProductPropertyRespone> responeList = new ArrayList<>();
        entityList.forEach(o -> {
            responeList.add(ProductPropertyRespone.builder()
                    .price(o.getPrice())
                    .priceNow(util.moneyToStringFormat(o.getPricePromotion() * o.getPrice() / 100))
                    .priceString(util.moneyToStringFormat(o.getPrice()))
                    .quantity(o.getQuantity())
                    .id(String.valueOf(o.getId()))
                    .color(colorService.findById(o.getColorId()))
                    .pricePromotion(o.getPricePromotion())
                    .pricePromotionString(util.moneyToStringFormat(o.getPricePromotion()))
                    .idPromotion(o.getPromotionId())
                    .build());
        });
        return responeList;
    }
}
