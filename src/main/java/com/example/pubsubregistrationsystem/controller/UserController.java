package com.example.pubsubregistrationsystem.controller;

import com.example.pubsubregistrationsystem.model.UserRegisteredEvent;
import com.example.pubsubregistrationsystem.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserRegisteredEvent registerUser(
            @RequestParam String name,
            @RequestParam String email) {
        return userService.registerUser(name, email);
    }
}
