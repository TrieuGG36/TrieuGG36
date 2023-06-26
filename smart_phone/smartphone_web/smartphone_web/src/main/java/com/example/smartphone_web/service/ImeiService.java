package com.example.smartphone_web.service;

import com.example.smartphone_web.dto.request.imei.ImeiRequest;
import com.example.smartphone_web.dto.respone.imei.ImeiResponse;

import java.util.List;


public interface ImeiService {

    List<ImeiResponse> findImeiDaBan(Long product, Long order);
}
