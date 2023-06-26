package com.example.smartphone_web.service;



import com.example.smartphone_web.dto.request.attribute.screen.ScreenRequest;
import com.example.smartphone_web.dto.respone.attribute.screen.ScreenReposne;

import java.util.List;

public interface IScreen {

     List<ScreenReposne> findAllScreen();

     String save(ScreenRequest request);

     String edit(ScreenRequest request);

     ScreenReposne findById(String id);

     String delete(Long id);
}
