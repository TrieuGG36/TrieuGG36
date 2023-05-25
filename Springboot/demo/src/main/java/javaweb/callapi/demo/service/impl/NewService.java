package javaweb.callapi.demo.service.impl;

import javaweb.callapi.demo.converter.NewConverter;
import javaweb.callapi.demo.dto.NewDTO;
import javaweb.callapi.demo.entities.CategoryEntity;
import javaweb.callapi.demo.entities.NewEntity;
import javaweb.callapi.demo.repository.CategoryRepository;
import javaweb.callapi.demo.repository.NewRepository;
import javaweb.callapi.demo.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NewService implements INewService {

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewConverter newConverter;

    @Override
    public NewDTO save(NewDTO newDTO) {
        NewEntity newEntity = new NewEntity();
        if (newDTO.getId() != null){
            NewEntity oldNewEntity = newRepository.getOne(newDTO.getId());
            newEntity = newConverter.toEntity(newDTO, oldNewEntity);
        }else{
            newEntity = newConverter.toEntity(newDTO);
        }
        CategoryEntity category = categoryRepository.findOneByCode(newDTO.getCategoryCode());
        newEntity.setCategory(category);
        newEntity = newRepository.save(newEntity);
        return newConverter.toDTO(newEntity);
    }

    @Override
    public void delete(long[] ids) {
        for (long item: ids) {
            newRepository.deleteById(item);
        }
    }

    @Override
    public List<NewDTO> findAll(Pageable pageable) {
        List<NewDTO> results = new ArrayList<>();
        List<NewEntity> entities = newRepository.findAll(pageable).getContent();
        for (NewEntity item : entities){
            NewDTO newDTO = newConverter.toDTO(item);
            results.add(newDTO);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int) newRepository.count();
    }

//    @Override
//    public NewDTO update(NewDTO newDTO) {
//        NewEntity oldNewEnity = newRepository.findOne(newDTO.getId());
//        NewEntity newEntity = newConverter.toEntity(newDTO, oldNewEnity);
//        CategoryEntity category = categoryRepository.findOneByCode(newDTO.getCategoryCode());
//        newEntity.setCategory(category);
//        newEntity = newRepository.save(newEntity);
//        return newConverter.toDTO(newEntity);
//    }
}
