package com.example.server.Scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
// import java.util.Date;
import java.util.stream.Collectors;

import com.example.server.Scholarship.repository.*;
import com.example.server.Scholarship.entity.*;
import com.example.server.studentprofile.entity.StudentProfile;
import com.example.server.studentprofile.repository.StudentProfileRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class ScholarshipController {

    @Autowired
    private ScholarshipRepository scholarshipRepository;
    @Autowired
    private ScholarshipTypeRepository scholarshipTypeRepository;
    @Autowired
    private StudentProfileRepository studentProfileRepository;
    @Autowired
    private ScholarshipOfficerRepository scholarshipOfficerRepository;

    ScholarshipController(ScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository;
    }

    @GetMapping("/scholarship")
    public Collection<Scholarship> Scholarship() {
        return scholarshipRepository.findAll().stream().collect(Collectors.toList());
    }
    
    @PostMapping("/scholarship/{scholarshipTypeid}/{studentproId}/{officerid}/{money}/{paydate}")
    public Scholarship newScholarship(@PathVariable long scholarshipTypeid, @PathVariable long studentproId,
            @PathVariable long officerid, @PathVariable Integer money,
            @PathVariable("paydate") @DateTimeFormat(pattern = "yyyy-mm-dd") Date paydate)

    {
        Scholarship scholarship = new Scholarship();
        scholarship.setPaydate(paydate);
        scholarship.setMoney(money);

        ScholarshipType scholarshipType = scholarshipTypeRepository.findById(scholarshipTypeid);
        scholarship.setScholarshipType(scholarshipType);

        StudentProfile student = studentProfileRepository.findById(studentproId);
        scholarship.setStudentProfile(student);

        ScholarshipOfficer scholarshipOfficer = scholarshipOfficerRepository.findById(officerid);
        scholarship.setScholarshipOfficer(scholarshipOfficer);

        return scholarshipRepository.save(scholarship);
    }
}