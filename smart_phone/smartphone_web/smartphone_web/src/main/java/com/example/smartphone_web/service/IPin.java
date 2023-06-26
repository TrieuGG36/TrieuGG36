package com.example.smartphone_web.service;


import com.example.smartphone_web.dto.request.attribute.pin.PinRequest;
import com.example.smartphone_web.dto.respone.attribute.pin.PinRespone;

import java.util.List;

public interface IPin {

    List<PinRespone> findAll();

    String addPin(PinRequest request);

    String editPin(PinRequest request);

    String delete(Long id);

    PinRespone findById(Long id);
}
