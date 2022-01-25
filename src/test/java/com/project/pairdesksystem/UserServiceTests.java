package com.project.pairdesksystem;

import com.project.pairdesksystem.User.models.User;
import com.project.pairdesksystem.User.models.UserService;
import com.project.pairdesksystem.User.repository.UserRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@Transactional
public class UserServiceTests {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void delete_User_With_Service() throws NotFoundException {

        User user = new User();
        boolean userWasDeleted = false;

        user.setUsername("testName");
        user.setEmail("testEmail@email.com");
        user.setPassword("12345");
        user.setUserId(98701L);
        userRepository.save(user);
        System.out.println(user.getUserId());

        List users = userService.getAllUsersDTO();

        userService.deleteByUserId(user.getUserId());

        List usersDeleted = userService.getAllUsersDTO();

        System.out.println("Hello");
        System.out.println(user.getUserId());
        System.out.println(users.size());
        System.out.println(usersDeleted.size());

        if(usersDeleted.size() < users.size()){
            userWasDeleted = true;
        }

        assertTrue(userWasDeleted);
    }
}