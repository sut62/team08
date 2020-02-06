package com.example.server.Activities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.server.Activities.entity.Institution;
import com.example.server.Activities.repository.InstitutionRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController public class InstitutionController {

    @Autowired
    private final InstitutionRepository institutionRepository;

    public InstitutionController(final InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }
    @GetMapping("/institution")
    public Collection<Institution> institutions() {
        return institutionRepository.findAll().stream().collect(Collectors.toList());
    }

}