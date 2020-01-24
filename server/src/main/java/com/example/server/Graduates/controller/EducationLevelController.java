package com.example.server.Graduates.controller;

import com.example.server.Graduates.entity.EducationLevel;
import com.example.server.Graduates.repository.EducationLevelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class EducationLevelController {

    @Autowired
    private EducationLevelRepository educationlevelRepository;

    public EducationLevelController(EducationLevelRepository educationlevelRepository) {
        this.educationlevelRepository = educationlevelRepository;
    }
    @GetMapping("/educationlevel")
    public Collection<EducationLevel> educationlevels() {
        return educationlevelRepository.findAll().stream().collect(Collectors.toList());
    }
}