package com.example.smartphone_web.service;


import com.example.smartphone_web.dto.request.attribute.rom.RomRequest;
import com.example.smartphone_web.dto.request.rom.RomRequestAdd;
import com.example.smartphone_web.dto.respone.attribute.ram.RamRespone;
import com.example.smartphone_web.dto.respone.rom.RomRespone;
import com.example.smartphone_web.entity.ProductEntity;

import java.util.List;

public interface IRom {
    List<RomRespone> findByProduct(Long id);

    String createRom(List<RomRequestAdd> romRequestAdds, ProductEntity entity);

    String createRomWithProductEdit(List<RomRequestAdd> romRequestAdds, ProductEntity entity);
    List<RomRespone> findAll();

    String saveRom(RomRequest request);

    String editRom(RomRequest request);

    RamRespone findById(String id);

    String deleteRom(Long id);


    List<RomRespone> findByAll();
    String save(RomRequest request);
    String update(RomRequest request);
    String delete(Long id);
    RomRespone findById(Long id);

}
