package com.example.server.studentprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.example.server.studentprofile.entity.*;
import com.example.server.studentprofile.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class StudentProfileController {

    @Autowired
    private StudentProfileRepository studentprofileRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private StatusRepository statusRepository;

    StudentProfileController(StudentProfileRepository studentprofileRepository, GenderRepository genderRepository,
            MajorRepository majorRepository, StatusRepository statusRepository) {

        this.studentprofileRepository = studentprofileRepository;
        this.genderRepository = genderRepository;
        this.majorRepository = majorRepository;
        this.statusRepository = statusRepository;
    }

    @GetMapping("/student")
    public Collection<StudentProfile> StudentProfiles() {
        return studentprofileRepository.findAll().stream().collect(Collectors.toList());
    }

    // http://localhost:9000/studentprofile/1/2020-01-08/1/1/%E0%B8%AB%E0%B8%81%E0%B8%9F%E0%B8%81/asd/%E0%B8%9F%E0%B8%AB%E0%B8%81/%E0%B8%9F%E0%B8%81%E0%B8%AB/13/asd/ads/asd
    // @GetMapping("/studentprofile/{majorId}/{birthday}/genderId}/{statusId}")
    @PostMapping("/studentprofile/{majorId}/{birthday}/{genderId}/{statusId}/{idnumber}/{namethai}/{nameeng}/{idcard}/{age}/{blood}/{tel}/{address}")

    public void newPay(@PathVariable long majorId, @PathVariable long genderId, @PathVariable long statusId,
            @PathVariable String blood, @PathVariable String address, @PathVariable String idnumber,
            @PathVariable String namethai, @PathVariable String nameeng, @PathVariable String idcard,
            @PathVariable String tel, @PathVariable Integer age,
            @PathVariable("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday

    ) {
        System.out.println("Doing");
        StudentProfile studentprofiles = new StudentProfile();
        studentprofiles.setBrithday(birthday);
        studentprofiles.setAddress(address);
        studentprofiles.setAge(age);
        studentprofiles.setBlood(blood);
        studentprofiles.setTel(tel);
        studentprofiles.setIdcard(idcard);
        studentprofiles.setIdnumber(idnumber);
        studentprofiles.setNameeng(nameeng);
        studentprofiles.setNamethai(namethai);
        Gender genders = genderRepository.findById(genderId);
        studentprofiles.setGender(genders);

        Major majors = majorRepository.findById(majorId);
        studentprofiles.setMajor(majors);

        Status statuss = statusRepository.findById(statusId);
        studentprofiles.setStatus(statuss);

        studentprofileRepository.save(studentprofiles);
    }
}