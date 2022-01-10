package com.project.pairdesksystem.User.controllers;


import com.project.pairdesksystem.User.models.UserDTO;
import com.project.pairdesksystem.User.models.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserResource {
    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("/api/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getAllUsersDTO() {
        return userService.getAllUsersDTO();
    }

    @CrossOrigin
    @GetMapping("/api/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public UserDTO findUser(@PathVariable long userId) throws NotFoundException {
        return userService.getUserDTOByUserId(userId);
    }
}
