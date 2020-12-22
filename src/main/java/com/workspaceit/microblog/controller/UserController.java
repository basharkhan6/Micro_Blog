package com.workspaceit.microblog.controller;

import com.workspaceit.microblog.model.User;
import com.workspaceit.microblog.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users")
    public void registerUser(@RequestBody @Valid User user) {
        userService.createUser(user);
    }

    @GetMapping("/api/users")
    public List<User> getAllUser() {
        return userService.findAllUser();
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable int id) {
        return userService.findUser(id);
    }

    @GetMapping("/api/users/{email}")
    public User getUser(@PathVariable String email) {
        return userService.findUser(email);
    }
}
