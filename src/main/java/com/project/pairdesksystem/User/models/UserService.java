package com.project.pairdesksystem.User.models;

import javassist.NotFoundException;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsersDTO();
    UserDTO getUserDTOByUserId(long userId) throws NotFoundException;
}
