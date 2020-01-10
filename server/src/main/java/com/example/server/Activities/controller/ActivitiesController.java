package com.example.server.activities.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.example.server.studentprofile.entity.*;
import com.example.server.studentprofile.repository.*;
import com.example.server.activities.entity.*;
import com.example.server.activities.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class ActivitiesController {

    @Autowired
    private ActivitiesRepository activitiesRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private SchoolYearRepository schoolyearRepository;
    @Autowired
    private StudentProfileRepository studentProfileRepository;

    ActivitiesController(ActivitiesRepository activitiesRepository, InstitutionRepository institutionRepository,
            SchoolYearRepository schoolyearRepository, StudentProfileRepository studentProfileRepository) {
        this.activitiesRepository = activitiesRepository;
        this.institutionRepository = institutionRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.schoolyearRepository = schoolyearRepository;
    }

    @GetMapping("/activities")
    public Collection<Activities> activitiess() {
        return activitiesRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/activities/{studentid}/{yearid}/{date}/{actname}/{insid}/{tel}")
    public Activities newActivities(@PathVariable long yearid,
            @PathVariable("date") @DateTimeFormat(pattern = "yyyy-mm-dd") Date date, @PathVariable String actname,
            @PathVariable long insid, @PathVariable long studentid, @PathVariable String tel) {

        Activities activities = new Activities();
        activities.setDate(date);
        activities.setActname(actname);
        activities.setTel(tel);
        StudentProfile students = studentProfileRepository.findById(studentid);
        activities.setStudentprofile(students);
        SchoolYear schoolyears = schoolyearRepository.findById(yearid);
        activities.setSchoolyear(schoolyears);
        Institution institutions = institutionRepository.findById(insid);
        activities.setInstitution(institutions);

        return activitiesRepository.save(activities);
    }
}
