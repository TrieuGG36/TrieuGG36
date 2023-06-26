package com.example.smartphone_web.service;



import com.example.smartphone_web.dto.request.attribute.chip.ChipRequestDto;
import com.example.smartphone_web.dto.respone.attribute.chip.ChipRespone;

import java.util.List;


public interface IChip {
    List<ChipRespone> findAll();

    ChipRespone findByCate(String id);

    String createChip(ChipRequestDto request);

    String updateChip(ChipRequestDto request);

    String deleteChip(Long idChip);
}
