package com.project.pairdesksystem.User.controllers;


import com.project.pairdesksystem.User.models.UserDTO;
import com.project.pairdesksystem.User.models.UserService;
import javassist.NotFoundException;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
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

    @CrossOrigin
    @GetMapping("/api/password")
    @PreAuthorize("hasRole('ADMIN')")
    public String generatePassword()  {
        CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit);
        CharacterRule upper = new CharacterRule(EnglishCharacterData.UpperCase);
        CharacterRule lower = new CharacterRule(EnglishCharacterData.LowerCase);
        CharacterRule specialCharacterRule = new CharacterRule(new CharacterData() {
            @Override
            public String getErrorCode() {
                return "SAMPLE_ERROR_CODE";
            }

            @Override
            public String getCharacters() {
                return "!@#_-";
            }
        });

        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String password = passwordGenerator.generatePassword(8, digits, upper, lower, specialCharacterRule);

        return password;
    }
}
