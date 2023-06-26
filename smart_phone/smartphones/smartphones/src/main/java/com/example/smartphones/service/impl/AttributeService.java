package com.example.smartphones.service.impl;

import com.example.smartphones.dto.respone.attribute.AttributeRespone;
import com.example.smartphones.entity.AttributeProductEntity;
import com.example.smartphones.repository.*;
import com.example.smartphones.service.IAttribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class AttributeService implements IAttribute {
    private final AttributeProductRepo repo;
    private final RamRepo ramRepo;
    private final ChipRepo chipRepo;
    private final OsRepo osRepo;
    private final PinRepo pinRepo;
    private final ScreenRepo screenRepo;
    private final CamRepo camRepo;
    @Override
    public AttributeRespone findByProduct(Long id) {
        AttributeProductEntity entity = repo.findByProductId(id);
        AttributeRespone respone = new AttributeRespone();
        respone.setOperatingSystem(osRepo.findById(entity.getOsId()).get().getName());
        respone.setScreen(screenRepo.findById(entity.getScreenId()).get().getName());
        respone.setRam(ramRepo.findById(entity.getRamID()).get().getName());
        respone.setPin(String.valueOf(pinRepo.findById(entity.getPinId()).get().getName()) + " mAh");
        respone.setChip(chipRepo.findById(entity.getChipId()).get().getName());
        respone.setCamSau(camRepo.findById(entity.getCamId()).get().getCamSau());
        respone.setCamTruoc(camRepo.findById(entity.getCamId()).get().getCamTruoc());
        return respone;
    }
}
