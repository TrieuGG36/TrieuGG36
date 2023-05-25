package javaweb.callapi.demo.service.impl;

import javaweb.callapi.demo.converter.UserConverter;
import javaweb.callapi.demo.dto.UserDTO;
import javaweb.callapi.demo.entities.UserEntity;
import javaweb.callapi.demo.repository.UserRepository;
import javaweb.callapi.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO save(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        if(userDTO.getId() != null){
            UserEntity oldUserEntity = userRepository.getOne(userDTO.getId());
            userEntity = userConverter.toEnity(userDTO, oldUserEntity);
        }else{
            userEntity = userConverter.toEnity(userDTO);
        }
        userEntity = userRepository.save(userEntity);
        return userConverter.toDTO(userEntity);
    }

    @Override
    public void delete(long[] ids) {
        for ( long item: ids) {
            userRepository.deleteById(item);
        }
    }
}
