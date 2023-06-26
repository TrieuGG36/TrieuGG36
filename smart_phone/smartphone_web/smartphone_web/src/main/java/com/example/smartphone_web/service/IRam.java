package com.example.smartphone_web.service;

import com.example.smartphone_web.dto.request.attribute.ram.RamRequest;
import com.example.smartphone_web.dto.respone.attribute.ram.RamRespone;

import java.util.List;

public interface IRam {
    List<RamRespone> findAll();

    String save(RamRequest request);

    String edit(RamRequest request);

    String delete(Long id);

    RamRespone findById(String id);

}
