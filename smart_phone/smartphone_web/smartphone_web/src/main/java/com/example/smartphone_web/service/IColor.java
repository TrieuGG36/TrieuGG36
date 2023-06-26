package com.example.smartphone_web.service;


import com.example.smartphone_web.dto.request.attribute.color.ColorRequest;
import com.example.smartphone_web.dto.respone.color.ColorRespone;

import java.util.List;

public interface IColor {
    public List<ColorRespone> findAll();
    ColorRespone findById(String id);

    String createColor(ColorRequest request);

    String updateColor(ColorRequest request);

    String deleteChip(Long id);

}
