//package com.example.smartphone_web.service.impl;
//
//import com.example.smartphone_web.dto.respone.imei.ImeiResponse;
//import com.example.smartphone_web.entity.ImeiEntity;
//import com.example.smartphone_web.repository.ImeiRepo;
//import com.example.smartphone_web.service.ImeiService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@Service
//public class ImeiServiceImpl implements ImeiService {
//    @Autowired
//    private ImeiRepo imeiRepo;
//
//
//    @Override
//    public List<ImeiResponse> findImeiDaBan(Long product, Long order) {
//        if(imeiRepo.findImeiDaBan(product, order) != null ){
//            return imeiRepo.findImeiDaBan(product, order).stream().map(this::maptoDto).collect(Collectors.toList());
//        }
//        return null;
//    }
//
//    private ImeiResponse maptoDto(ImeiEntity entity){
//        return new ImeiResponse(String.valueOf(entity.getId()), entity.getValue());
//    }
//}
