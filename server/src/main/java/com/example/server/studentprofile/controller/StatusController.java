package com.example.server.studentprofile.controller;

import com.example.server.studentprofile.entity.Status;
import com.example.server.studentprofile.repository.StatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class StatusController {

    @Autowired
    private final StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }
    @GetMapping("/status")
    public Collection<Status> statuss() {
        return statusRepository.findAll().stream().collect(Collectors.toList());
    }
}