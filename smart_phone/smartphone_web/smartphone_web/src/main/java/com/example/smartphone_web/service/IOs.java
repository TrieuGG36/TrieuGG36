package com.example.smartphone_web.service;

import com.example.smartphone_web.dto.request.attribute.os.OsRequest;
import com.example.smartphone_web.dto.respone.attribute.os.OsRespone;

import java.util.List;

public interface IOs {
    List<OsRespone> findAll();

    String save(OsRequest request);

    String edit(OsRequest request);

    OsRespone findById(String id);

    String delete(Long id);
}
