package javaweb.callapi.demo.converter;

import javaweb.callapi.demo.dto.CategoryDTO;
import javaweb.callapi.demo.entities.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {


    public CategoryEntity toEntity(CategoryDTO categoryDTO){
        CategoryEntity entity = new CategoryEntity();
        entity.setCode(categoryDTO.getCode());
        entity.setName(categoryDTO.getName());
        return entity;
    }

    public CategoryDTO toDTO(CategoryEntity categoryEntity){
        CategoryDTO dto = new CategoryDTO();
        dto.setId(categoryEntity.getId());
        dto.setCreatedDate(categoryEntity.getCreatedDate());
        dto.setModifiedDate(categoryEntity.getModifiedDate());
        dto.setCode(categoryEntity.getCode());
        dto.setName(categoryEntity.getName());
        return dto;
    }

    public CategoryEntity toEntity(CategoryDTO categoryDTO, CategoryEntity entity){
        entity.setCode(categoryDTO.getCode());
        entity.setName(categoryDTO.getName());
        return entity;
    }
}
