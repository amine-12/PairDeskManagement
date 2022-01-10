package com.project.pairdesksystem.User.models;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings(
            {
                    @Mapping(target = "id", ignore = true),
            }
    )
    User userDTOToUser(UserDTO userDTO);
    UserDTO userToUserDTO(User user);

    List<User> userDTOListToUserList(List<UserDTO> userDTOList);

    List<UserDTO> userListToUserDTOList(List<User> userList);
}
