package javaweb.callapi.demo.api;

import javaweb.callapi.demo.dto.UserDTO;
import javaweb.callapi.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserAPI {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/user")
    public UserDTO create(@RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }

    @PutMapping(value = "/user/{id}")
    public UserDTO update(@RequestBody UserDTO userDTO, @PathVariable("id") long id){
        userDTO.setId(id);
        return userService.save(userDTO);
    }


    @DeleteMapping(value = "/user")
    public void delete(@RequestBody long[] ids){
        userService.delete(ids);
    }

}
