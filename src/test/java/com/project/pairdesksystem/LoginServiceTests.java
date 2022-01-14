//package com.project.pairdesksystem;
//
//import com.project.pairdesksystem.User.WebSecurityConfig;
//import com.project.pairdesksystem.User.models.*;
//import com.project.pairdesksystem.User.repository.UserRepository;
//import javassist.NotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DataJpaTest
//@ExtendWith(SpringExtension.class)
//public class LoginServiceTests {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserRepository userRepository;
//
//    @BeforeEach
//    public void setUpDb(){
//        User user = new User();
//        user.setId(52356L);
//        user.setUserId(97645L);
//        user.setEmail("testuser@gmail.com");
//        user.setUsername("testuser");
//        user.setPassword("password");
//        Set<Role> role = new HashSet<>();
//        role.add(new Role(ERole.ROLE_ADMIN));
//        user.setRoles(role);
//        userRepository.save(user);
//
//    }
//
//    @Test
//    void get_all_users() throws NotFoundException {
//        UserDTO userDTO = userService.getUserDTOByUserId(97645L);
//        System.out.println(userDTO.getUsername() + " ffasefa");
//        assertEquals( userDTO.getUsername(), "testuser");
//    }
//}
