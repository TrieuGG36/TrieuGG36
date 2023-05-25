package javaweb.callapi.demo.api;


import javaweb.callapi.demo.dto.CategoryDTO;
import javaweb.callapi.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CategoryAPI {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping(value = "/cate")
    public CategoryDTO create(@RequestBody CategoryDTO model){
        return categoryService.save(model);
    }

    @PutMapping(value = "/cate/{id}")
    public CategoryDTO update(@RequestBody CategoryDTO model, @PathVariable("id") long id){
        model.setId(id);
        return categoryService.save(model);
    }

    @DeleteMapping(value = "/cate")
    public void delete(@RequestBody long[] ids){

        categoryService.delete(ids);
    }
}
