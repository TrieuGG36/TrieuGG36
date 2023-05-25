package javaweb.callapi.demo.service;

import javaweb.callapi.demo.dto.UserDTO;

public interface IUserService {

    UserDTO save(UserDTO userDTO);

    void delete(long[] ids);
}
