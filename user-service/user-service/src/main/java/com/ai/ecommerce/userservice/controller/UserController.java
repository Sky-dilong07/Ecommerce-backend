package com.ai.ecommerce.userservice.controller;

import com.ai.ecommerce.userservice.entity.User;
import com.ai.ecommerce.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // ---------------- GET ALL USERS ----------------
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ---------------- GET USER BY ID ----------------
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // ---------------- REGISTER USER ----------------
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // ---------------- CREATE USER ----------------
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // ---------------- UPDATE USER ----------------
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    // ---------------- DELETE USER ----------------
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully!";
    }
}

