package com.example.smartphone_web.service.impl;

import com.example.smartphone_web.dto.request.attribute.chip.ChipRequestDto;
import com.example.smartphone_web.dto.respone.attribute.chip.ChipRespone;
import com.example.smartphone_web.entity.ChipEntity;
import com.example.smartphone_web.repository.ChipRepo;
import com.example.smartphone_web.service.IChip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChipService implements IChip {
    private final ChipRepo repo;


    @Override
    public List<ChipRespone> findAll() {
        List<ChipRespone> list = new ArrayList<>();
        List<ChipEntity> entities = repo.findByDeleteFlagIsFalse();
        for (ChipEntity e: entities
        ) {
            list.add(new ChipRespone(String.valueOf(e.getId()), e.getName()));
        }
        return list;
    }

    @Override
    public ChipRespone findByCate(String id) {
        ChipEntity chip = repo.getById(Long.valueOf(id));
        return new ChipRespone(String.valueOf(chip.getId()), chip.getName());
    }

    @Override
    public String createChip(ChipRequestDto request) {
        ChipEntity chip = new ChipEntity();
        chip.setName(request.getName());
        chip.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        chip.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
        repo.save(chip);
        return "ok";
    }

    @Override
    public String updateChip(ChipRequestDto request) {
        ChipEntity chip = new ChipEntity();
        chip.setName(request.getName());
        chip.setId(Long.valueOf(request.getId()));
        chip.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        chip.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
        repo.save(chip);
        return "ok";
    }

    @Override
    public String deleteChip(Long id) {
        ChipEntity chip = repo.getById(id);
        chip.setDeleteFlag(true);
        repo.save(chip);
        return "ok";
    }
}
