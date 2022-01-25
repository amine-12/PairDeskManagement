package com.project.pairdesksystem.User.models;

import com.project.pairdesksystem.User.repository.UserRepository;
import com.project.pairdesksystem.datalayer.Feature.Feature;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<UserDTO> getAllUsersDTO(){
        return userMapper.userListToUserDTOList(getAllUsers());
    }

    public User getUserByUserId(long userId) throws NotFoundException {
        return userRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException("No user found for userId: " + userId));
    }

    @Override
    public UserDTO getUserDTOByUserId(long userId) throws NotFoundException {
        return userMapper.userToUserDTO(getUserByUserId(userId));
    }

    @Override
    public void deleteByUserId(long userId) {
        User user = userRepository.findByUserId(userId).orElse(new User());
        if(user.getUserId() != null)
            userRepository.deleteByUserId(userId);

        log.debug("user deleted");
    }
}
