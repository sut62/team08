package com.example.server.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.server.User.repository.UserTypeRepository;
import com.example.server.User.entity.UserType;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UserTypeController {

    @Autowired
    private UserTypeRepository userTypeRepository;

    public UserTypeController(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }
    @GetMapping("/usertypes")
    public Collection<UserType> getUserTypes() {
        return userTypeRepository.findAll().stream().collect(Collectors.toList());
    }
}