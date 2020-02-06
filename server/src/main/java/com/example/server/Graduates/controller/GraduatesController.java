package com.example.server.Graduates.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.example.server.Activities.repository.*;
import com.example.server.Graduates.entity.*;
import com.example.server.Graduates.repository.*;
import com.example.server.Activities.entity.*;
import com.example.server.studentprofile.entity.StudentProfile;
import com.example.server.studentprofile.repository.StudentProfileRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class GraduatesController {

    @Autowired
    private GraduatesRepository graduatesRepository;
    @Autowired
    private StudentProfileRepository studentprofileRepository;
    @Autowired
    private EducationLevelRepository educationlevelRepository;
    @Autowired
    private SchoolYearRepository schoolyearRepository;

    GraduatesController(GraduatesRepository graduatesRepository,
            StudentProfileRepository studentprofileRepository,
            EducationLevelRepository educationlevelRepository, 
            SchoolYearRepository schoolyearRepository) {

        this.graduatesRepository = graduatesRepository;
        this.studentprofileRepository = studentprofileRepository;
        this.educationlevelRepository = educationlevelRepository;
        this.schoolyearRepository = schoolyearRepository;
    }

    public SchoolYearRepository getSchoolyearRepository() {
        return schoolyearRepository;
    }

    public void setSchoolyearRepository(final SchoolYearRepository schoolyearRepository) {
        this.schoolyearRepository = schoolyearRepository;
    }

    public EducationLevelRepository getEducationlevelRepository() {
        return educationlevelRepository;
    }

    public void setEducationlevelRepository(final EducationLevelRepository educationlevelRepository) {
        this.educationlevelRepository = educationlevelRepository;
    }

    public GraduatesRepository getGraduatesRepository() {
        return graduatesRepository;
    }

    public void setGraduatesRepository(final GraduatesRepository graduatesRepository) {
        this.graduatesRepository = graduatesRepository;
    }

    @GetMapping("/graduates")
    public Collection<Graduates> Graduatess() {
        return graduatesRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/graduates/{studentproId}/{yearid}/{educaId}/{graduadate}/{honor}/{graduation}")
    public Graduates newGraduates(
            @PathVariable String honor, 
            @PathVariable String graduation,
            @PathVariable long educaId,
            @PathVariable long yearid,
            @PathVariable long studentproId,
            @PathVariable("graduadate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date graduadate
    ) {
        System.out.println("Doing");
        System.out.println(honor);
        System.out.println(graduadate);
        System.out.println(graduation);
        Graduates graduates = new Graduates();
        graduates.setHonor(honor);
        graduates.setGraduadate(graduadate);
        graduates.setGraduation(graduation);
        System.out.println(graduates);

        EducationLevel educationlevels = educationlevelRepository.findById(educaId);
        graduates.setEducationlevel(educationlevels);

        SchoolYear schoolyears = schoolyearRepository.findById(yearid);
        graduates.setSchoolyear(schoolyears);

        StudentProfile studentprofiles = studentprofileRepository.findById(studentproId);
        graduates.setStudentprofile(studentprofiles);

        return graduatesRepository.save(graduates);
    }
}