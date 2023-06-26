package com.example.smartphones.service.impl;

import com.example.smartphones.dto.respone.rom.RomRespone;
import com.example.smartphones.entity.RomEntity;
import com.example.smartphones.repository.RomRepo;
import com.example.smartphones.service.IRom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RomService implements IRom {
    private final RomRepo repo;

    private final ProductPropertyService productPropertyService;
    @Override
    public List<RomRespone> findByProductId(Long id) {
        List<RomRespone> list = new ArrayList<>();
        List<RomEntity> entities = repo.findByProductId(id);
        entities.forEach(o -> {
            if (productPropertyService.findByRomId(o.getId()) != null && productPropertyService.findByRomId(o.getId()).size() > 0){
                list.add(RomRespone.builder().name(o.getName()).id(String.valueOf(o.getId())).productPropertyRespones(productPropertyService.findByRomId(o.getId())).build());
            }
        });

        return list;
    }


    private RomRespone mapToEntity(RomEntity entity){
        RomRespone respone = new RomRespone();
        respone.setId(String.valueOf(entity.getId()));
        respone.setName(entity.getName());
        return respone;
    }
}
