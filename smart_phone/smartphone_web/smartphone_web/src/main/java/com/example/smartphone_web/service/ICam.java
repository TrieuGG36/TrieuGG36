package com.example.smartphone_web.service;

import com.example.smartphone_web.dto.request.attribute.cam.CamRequest;
import com.example.smartphone_web.dto.respone.attribute.cam.CamRespone;

import java.util.List;


public interface ICam {
    List<CamRespone> findAll();

    String save(CamRequest request);

    String update(CamRequest request);

    String delete(Long id);

    CamRespone findByID(Long id);
}
