package javaweb.callapi.demo.service;

import javaweb.callapi.demo.dto.CategoryDTO;

public interface ICategoryService {

    CategoryDTO save(CategoryDTO categoryDTO);

    void delete(long[] ids);
}
