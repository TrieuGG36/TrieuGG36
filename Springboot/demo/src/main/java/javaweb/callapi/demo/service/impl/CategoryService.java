package javaweb.callapi.demo.service.impl;

import javaweb.callapi.demo.converter.CategoryConverter;
import javaweb.callapi.demo.dto.CategoryDTO;
import javaweb.callapi.demo.entities.CategoryEntity;
import javaweb.callapi.demo.repository.CategoryRepository;
import javaweb.callapi.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter cateConverter;

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        CategoryEntity entity = new CategoryEntity();
        if (categoryDTO.getId() != null){
            CategoryEntity oldCateEntity = categoryRepository.getOne(categoryDTO.getId());
            entity = cateConverter.toEntity(categoryDTO, oldCateEntity);
        }else{
            entity = cateConverter.toEntity(categoryDTO);
        }
        entity = categoryRepository.save(entity);
        return cateConverter.toDTO(entity);
    }

    @Override
    public void delete(long[] ids) {
        for (long item: ids) {
            categoryRepository.deleteById(item);
        }
    }
}
