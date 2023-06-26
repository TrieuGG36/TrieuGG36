package com.example.smartphone_web.service.impl;

import com.example.smartphone_web.dto.request.attribute.screen.ScreenRequest;
import com.example.smartphone_web.dto.respone.attribute.screen.ScreenReposne;
import com.example.smartphone_web.entity.ScreenEntity;
import com.example.smartphone_web.repository.ScreenRepo;
import com.example.smartphone_web.service.IScreen;

import com.example.smartphone_web.until.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScreenService implements IScreen {

    private final ScreenRepo repo;

    private SessionUtil sessionUtil;

    @Override
    public List<ScreenReposne> findAllScreen() {
        List<ScreenEntity> entityList = repo.findByDeleteFlagIsFalse();
        List<ScreenReposne> reposnes = new ArrayList<>();
        /*for (ScreenEntity e: entityList
             ) {
            ScreenReposne reposne = new ScreenReposne(e.getId(), e.getName(), e.getLoaiScreenEntity().getId());
            reposnes.add(reposne);
        }*/
        return reposnes;
    }

    @Override
    public String save(ScreenRequest request) {
        ScreenEntity entity = new ScreenEntity();
//        LoaiScreenEntity loaiScreenEntity = new LoaiScreenEntity();
//        loaiScreenEntity.setId(request.getLoaiScreenId());
        entity.setId(request.getId());
//        entity.setLoaiScreenEntity(loaiScreenEntity);
        entity.setName(request.getName());
        entity.setCreateBy("ADMIN");
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierBy("ADMIN");
        entity.setDeleteFlag(false);
        repo.save(entity);
        return "ok";
    }

    @Override
    public String edit(ScreenRequest request) {
        ScreenEntity entity = repo.getById(request.getId());
//        LoaiScreenEntity loaiScreenEntity = new LoaiScreenEntity();
//        loaiScreenEntity.setId(request.getLoaiScreenId());
        entity.setName(request.getName());
//        entity.setLoaiScreenEntity(loaiScreenEntity);
        repo.save(entity);
        return "ok";
    }

    @Override
    public ScreenReposne findById(String id) {
        ScreenEntity entity = repo.getById(Long.valueOf(id));
        return new ScreenReposne(entity.getId(), entity.getName(), entity.getId());
    }

    /*@Override
    public ScreenReposne findById(String id) {
        ScreenEntity entity = repo.getById(Long.valueOf(id));
        return new ScreenReposne(entity.getId(), entity.getName(), entity.getLoaiScreenEntity().getId());
    }*/

    @Override
    public String delete(Long id) {
        ScreenEntity entity = repo.getById(id);
        entity.setDeleteFlag(true);
        repo.save(entity);
        return "ok";
    }

}
