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
//@ExtendWith(SpringExtension.class)
//public class LoginServiceTests {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Test
//    void get_all_users() throws NotFoundException {
//        User user2 = new User("username","test@hotmail.com","12345678");
//        user2.setUserId(12345L);
//        userRepository.save(user2);
//        System.out.println(user2.getUsername() + " ffasefa");
//        List<UserDTO> list = userService.getAllUsersDTO();
//        assertEquals( list.size(), 1);
//    }
//}
