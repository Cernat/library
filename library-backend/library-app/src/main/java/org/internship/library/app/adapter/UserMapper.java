package org.internship.library.app.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.internship.library.api.dto.UserDTO;
import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.security.UserRole;

public class UserMapper
{

    public static UserDTO userEntityToUserDTO(UserEntity user)
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserRole(String.valueOf(user.getUserRole()));
        return userDTO;
    }

    public static List<UserDTO> listOfUsersEntityToListOfUsersDTO(List<UserEntity> userEntities)
    {
        return userEntities.stream().map(UserMapper::userEntityToUserDTO).collect(Collectors.toList());
    }

    public static UserEntity userDTOtoUserEntity(UserDTO userDTO)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setUserRole(UserRole.valueOf(userDTO.getUserRole()));
        return userEntity;
    }

    public static List<UserEntity> listOfUsersDTOtoListOfUserEntities(List<UserDTO> userDTOList)
    {
        return userDTOList.stream().map(UserMapper::userDTOtoUserEntity).collect(Collectors.toList());
    }
}
