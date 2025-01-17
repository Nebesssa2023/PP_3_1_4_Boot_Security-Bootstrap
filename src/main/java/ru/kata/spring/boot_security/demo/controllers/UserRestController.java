package ru.kata.spring.boot_security.demo.controllers;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.entity.Author;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Author(name = "Victor Gabbasov", dateOfCreation = 2022)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserRestController {
    UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> showUser(Principal principal) {
        User user = userService.findByUserName(principal.getName());

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
