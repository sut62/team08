package com.example.server.Discipline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.server.Discipline.entity.Punish;
import com.example.server.Discipline.repository.PunishRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PunishController {

    @Autowired
    private PunishRepository punishRepository;

    public PunishController(PunishRepository punishRepository) {
        this.punishRepository = punishRepository;
    }
    @GetMapping("/punishs")
    public Collection<Punish> Punishs() {
        return punishRepository.findAll().stream().collect(Collectors.toList());
    }
}