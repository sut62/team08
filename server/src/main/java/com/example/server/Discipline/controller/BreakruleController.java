package com.example.server.Discipline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.server.Discipline.entity.Breakrule;
import com.example.server.Discipline.repository.BreakruleRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BreakruleController {

    @Autowired
    private BreakruleRepository breakruleRepository;

    public BreakruleController(BreakruleRepository breakruleRepository) {
        this.breakruleRepository = breakruleRepository;
    }
    @GetMapping("/breakrules")
    public Collection<Breakrule> Breakrules() {
        return breakruleRepository.findAll().stream().collect(Collectors.toList());
    }
}