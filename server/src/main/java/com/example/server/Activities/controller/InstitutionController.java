package com.example.server.activities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.server.activities.entity.Institution;
import com.example.server.activities.repository.InstitutionRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController public class InstitutionController {

    @Autowired
    private InstitutionRepository institutionRepository;

    public InstitutionController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }
    @GetMapping("/institution")
    public Collection<Institution> institutions() {
        return institutionRepository.findAll().stream().collect(Collectors.toList());
    }

}