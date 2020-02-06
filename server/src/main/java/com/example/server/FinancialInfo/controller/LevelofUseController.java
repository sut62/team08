package com.example.server.FinancialInfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.server.FinancialInfo.entity.LevelofUse;
import com.example.server.FinancialInfo.repository.LevelofUseRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class LevelofUseController {

    @Autowired
    private LevelofUseRepository levelofuseRepository;

    public LevelofUseController(LevelofUseRepository levelofuseRepository) {
        this.levelofuseRepository = levelofuseRepository;
    }
    @GetMapping("/levelofuses")
    public Collection<LevelofUse> LevelofUses() {
        return levelofuseRepository.findAll().stream().collect(Collectors.toList());
    }
}