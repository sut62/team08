package com.example.server.StudentHealthRecord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.server.StudentHealthRecord.entity.CongenitalDisease;
import com.example.server.StudentHealthRecord.repository.CongenitalDiseaseRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CongenitalDiseaseController {

    @Autowired
    private CongenitalDiseaseRepository congenitalDiseaseRepository;

    public CongenitalDiseaseController(CongenitalDiseaseRepository congenitalDiseaseRepository) {
        this.congenitalDiseaseRepository = congenitalDiseaseRepository;
    }
    @GetMapping("/congenitalDisease")
    public Collection<CongenitalDisease> congenitalDiseases(){
        return congenitalDiseaseRepository.findAll().stream().collect(Collectors.toList())  ;
    }


}