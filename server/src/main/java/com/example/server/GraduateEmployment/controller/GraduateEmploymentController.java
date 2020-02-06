package com.example.server.GraduateEmployment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.example.server.GraduateEmployment.entity.*;
import com.example.server.GraduateEmployment.repository.*;

import com.example.server.studentprofile.entity.StudentProfile;
import com.example.server.studentprofile.repository.StudentProfileRepository;

import com.example.server.Graduates.entity.*;
import com.example.server.Graduates.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class GraduateEmploymentController {

    @Autowired
    private GraduateEmploymentRepository graduateemploymentRepository;

    @Autowired
    private StudentProfileRepository studentprofileRepository;

    @Autowired
    private EducationLevelRepository educationlevelRepository;

    @Autowired
    private WorkStatusRepository workstatusRepository;

    GraduateEmploymentController(GraduateEmploymentRepository graduateemploymentRepository,
            StudentProfileRepository studentprofileRepository, EducationLevelRepository educationlevelRepository,
            WorkStatusRepository workstatusRepository) {

        this.graduateemploymentRepository = graduateemploymentRepository;
        this.studentprofileRepository = studentprofileRepository;
        this.educationlevelRepository = educationlevelRepository;
        this.workstatusRepository = workstatusRepository;
    }

    @GetMapping("/graduateemployment")
    public Collection<GraduateEmployment> GraduateEmployments() {
        return graduateemploymentRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/graduateemployment/{studentproId}/{workstatusId}/{educaId}/{workplace}/{jopposition}/{email}")
    public GraduateEmployment newGraduateEmployment(

            @PathVariable long educaId, @PathVariable String workplace, @PathVariable long studentproId,
            @PathVariable long workstatusId, @PathVariable String jopposition, @PathVariable String email

    ) {
        GraduateEmployment graduateemployment = new GraduateEmployment();
        graduateemployment.setWorkplace(workplace);
        graduateemployment.setJopposition(jopposition);
        graduateemployment.setEmail(email);

        EducationLevel educationlevels = educationlevelRepository.findById(educaId);
        graduateemployment.setEducationLevel(educationlevels);

        WorkStatus workstatus = workstatusRepository.findById(workstatusId);
        graduateemployment.setWorkStatus(workstatus);

        StudentProfile studentprofiles = studentprofileRepository.findById(studentproId);
        graduateemployment.setStudentprofile(studentprofiles);

        return graduateemploymentRepository.save(graduateemployment);
    }
}