package com.project.pairdesksystem.User.models;

import javassist.NotFoundException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserService {
    List<UserDTO> getAllUsersDTO();
    UserDTO getUserDTOByUserId(long userId) throws NotFoundException;

    void deleteByUserId(long userId);
}
