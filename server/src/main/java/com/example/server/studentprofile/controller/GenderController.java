package com.example.server.studentprofile.controller;

import com.example.server.studentprofile.entity.Gender;
import com.example.server.studentprofile.repository.GenderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class GenderController {

    @Autowired
    private final GenderRepository genderRepository;

    public GenderController(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }
    @GetMapping("/gender")
    public Collection<Gender> genders() {
        return genderRepository.findAll().stream().collect(Collectors.toList());
    }
}