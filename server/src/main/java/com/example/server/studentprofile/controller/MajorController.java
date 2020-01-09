package com.example.server.studentprofile.controller;

import com.example.server.studentprofile.entity.Major;
import com.example.server.studentprofile.repository.MajorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class MajorController {

    @Autowired
    private final MajorRepository majorRepository;

    public MajorController(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }
    @GetMapping("/major")
    public Collection<Major> majors() {
        return majorRepository.findAll().stream().collect(Collectors.toList());
    }
}