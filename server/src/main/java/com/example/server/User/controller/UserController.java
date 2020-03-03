package com.example.server.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.server.User.repository.UserRepository;
import com.example.server.User.entity.User;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/users")
    public Collection<User> getUsers() {
        return userRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/user/{username}/{password}")
    public User login(@PathVariable String username, @PathVariable String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}