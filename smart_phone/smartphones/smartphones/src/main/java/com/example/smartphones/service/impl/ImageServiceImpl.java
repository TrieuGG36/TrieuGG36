package com.example.smartphones.service.impl;


import com.example.smartphones.entity.ImageEntity;
import com.example.smartphones.repository.ImageRepo;
import com.example.smartphones.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepo repo;
    @Override
    public List<String> getAllImageByProduct(String productId) {
        List<String> stringList = new ArrayList<>();
        List<ImageEntity> entityList = repo.findByProductIdAndDeleteFlagIsFalse(Long.valueOf(productId));
        entityList.forEach(o -> {
            stringList.add(o.getLing_image());
        });
        return stringList;
    }
}
