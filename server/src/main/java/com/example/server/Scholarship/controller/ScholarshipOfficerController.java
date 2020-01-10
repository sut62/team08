package com.example.server.Scholarship.controller;

import com.example.server.Scholarship.entity.ScholarshipOfficer;
import com.example.server.Scholarship.repository.ScholarshipOfficerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ScholarshipOfficerController {

    @Autowired
    private final ScholarshipOfficerRepository scholarshipOfficerRepository;

    public ScholarshipOfficerController(ScholarshipOfficerRepository scholarshipOfficerRepository) {
        this.scholarshipOfficerRepository = scholarshipOfficerRepository;
    }
    @GetMapping("/scholarshipOfficers")
    public Collection<ScholarshipOfficer> ScholarshipOfficers() {
        return scholarshipOfficerRepository.findAll().stream().collect(Collectors.toList());
    }
}