package com.project.pairdesksystem;

import com.project.pairdesksystem.User.models.ERole;
import com.project.pairdesksystem.User.models.Role;
import com.project.pairdesksystem.User.models.User;
import com.project.pairdesksystem.User.repository.UserRepository;
import com.project.pairdesksystem.datalayer.Task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class LoginAuthAPITests {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUpDb(){
        User user = new User();
        user.setId(65431L);
        user.setUserId(52342L);
        user.setEmail("testuser@gmail.com");
        user.setUsername("testuser");
        user.setPassword("password");
        Set<Role> role = new HashSet<>();
        role.add(new Role(ERole.ROLE_ADMIN));
        user.setRoles(role);
        userRepository.save(user);

        User user2 = new User();
        user2.setId(642L);
        user2.setUserId(96422L);
        user2.setEmail("testuser2@gmail.com");
        user2.setUsername("testuser2");
        user2.setPassword("password");
        Set<Role> role2 = new HashSet<>();
        role.add(new Role(ERole.ROLE_USER));
        user2.setRoles(role2);
        userRepository.save(user2);
    }

    @Test
    void saveUser() {
        assertEquals( userRepository.count(), 2);
    }

    @Test
    void get_All_Users(){
        userRepository.findAll();
        assertEquals(userRepository.findAll().size(), 2);
    }


}
