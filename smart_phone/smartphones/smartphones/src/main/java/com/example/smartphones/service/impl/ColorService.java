package com.example.smartphones.service.impl;

import com.example.smartphones.entity.ColorEntity;
import com.example.smartphones.repository.ColorRepo;
import com.example.smartphones.service.IColor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ColorService implements IColor {
    private final ColorRepo repo;

    @Override
    public String findById(Long id) {
        ColorEntity entity = repo.findByDeleteFlagIsFalseAndId(id);
        if(entity != null){
            return entity.getValueColor();
        }
        return null;
    }
}
