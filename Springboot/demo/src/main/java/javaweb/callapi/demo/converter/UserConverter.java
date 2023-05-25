package javaweb.callapi.demo.converter;

import javaweb.callapi.demo.dto.UserDTO;
import javaweb.callapi.demo.entities.UserEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity toEnity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setFullName(userDTO.getFullname());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setStatus(userDTO.getStatus());
        userEntity.setUserName(userDTO.getUsername());
        return userEntity;
    }

    public UserDTO toDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setFullname(userEntity.getFullName());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setModifiedDate(userEntity.getModifiedDate());
        userDTO.setCreatedDate(userEntity.getCreatedDate());
        userDTO.setStatus(userEntity.getStatus());
        userDTO.setUsername(userEntity.getUserName());
        String hashed = BCrypt.hashpw(userEntity.getPassword(), BCrypt.gensalt());
        return userDTO;
    }

    public UserEntity toEnity(UserDTO userDTO, UserEntity userEntity){
        userEntity.setFullName(userDTO.getFullname());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setStatus(userDTO.getStatus());
        userEntity.setUserName(userDTO.getUsername());
        return userEntity;
    }
}
