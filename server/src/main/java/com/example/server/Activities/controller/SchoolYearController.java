package com.example.server.Activities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.server.Activities.entity.SchoolYear;
import com.example.server.Activities.repository.SchoolYearRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class SchoolYearController {

    @Autowired
    private SchoolYearRepository schoolyearRepository;

    public SchoolYearController(SchoolYearRepository schoolyearRepository) {
        this.schoolyearRepository = schoolyearRepository;
    }

    @GetMapping("/schoolyear")
    public Collection<SchoolYear> SchoolYears() {
        return schoolyearRepository.findAll().stream().collect(Collectors.toList());
    }
}
