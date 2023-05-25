package javaweb.callapi.demo.api;

import javaweb.callapi.demo.api.output.Output;
import javaweb.callapi.demo.dto.NewDTO;
import javaweb.callapi.demo.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class NewAPI {

    @Autowired
    private INewService newService;

    @GetMapping(value = "/new")
    public Output showNew(@RequestParam("page") int page, @RequestParam("limit") int limit){
        Output result = new Output();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(newService.findAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (newService.totalItem()) / limit));
        return result;
    }


    @PostMapping(value = "/new")
    public NewDTO createNew(@RequestBody NewDTO model){
        return newService.save(model);
    }

    @PutMapping(value = "/new/{id}")
    public NewDTO updateNew(@RequestBody NewDTO model, @PathVariable("id") long id){
        model.setId(id);
        return newService.save(model);
    }

    @DeleteMapping(value = "/new")
    public void deteleNew(@RequestBody long[] ids){

        newService.delete(ids);
    }

}
