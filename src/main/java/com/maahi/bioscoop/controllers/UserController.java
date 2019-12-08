package com.maahi.bioscoop.controllers;

import com.maahi.bioscoop.entities.User;
import com.maahi.bioscoop.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User newUser) {
        System.out.println("register");

        return userService.register(newUser);
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginUser) {
        System.out.println("login");

        return userService.login(loginUser);
    }

    @PostMapping("/logout")
    public boolean logout(@RequestBody String token) {
        System.out.println("Logout works!");
        return true;
    }
}
