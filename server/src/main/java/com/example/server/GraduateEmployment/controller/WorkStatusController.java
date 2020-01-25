package com.example.server.GraduateEmployment.controller;

import com.example.server.GraduateEmployment.entity.WorkStatus;
import com.example.server.GraduateEmployment.repository.WorkStatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class WorkStatusController {

    @Autowired
    private WorkStatusRepository workstatusRepository;

    public WorkStatusController(WorkStatusRepository workstatusRepository) {
        this.workstatusRepository = workstatusRepository;
    }
    @GetMapping("/workstatus")
    public Collection<WorkStatus> workstatus() {
        return workstatusRepository.findAll().stream().collect(Collectors.toList());
    }
}