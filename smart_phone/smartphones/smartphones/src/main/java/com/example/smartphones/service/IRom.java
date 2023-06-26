package com.example.smartphones.service;

import com.example.smartphones.dto.respone.rom.RomRespone;

import java.util.List;

public interface IRom {

    List<RomRespone> findByProductId(Long id);
}
