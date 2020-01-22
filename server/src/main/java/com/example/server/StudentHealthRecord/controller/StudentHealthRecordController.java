package com.example.server.StudentHealthRecord.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.server.StudentHealthRecord.entity.CongenitalDisease;
import com.example.server.StudentHealthRecord.entity.DrugAllergyHistory;
import com.example.server.StudentHealthRecord.entity.StudentHealthRecord;
import com.example.server.StudentHealthRecord.repository.*;
import com.example.server.studentprofile.entity.*;
import com.example.server.studentprofile.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class StudentHealthRecordController {

    @Autowired
    private StudentHealthRecordRepository studentHealthRecordRepository;
    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private CongenitalDiseaseRepository congenitalDiseaseRepository;
    @Autowired
    private DrugAllergyHistoryRepository drugAllergyHistoryRepository;

    StudentHealthRecordController(StudentHealthRecordRepository studentHealthRecordRepository,
            StudentProfileRepository studentProfileRepository,
            DrugAllergyHistoryRepository drugAllergyHistoryRepository,
            CongenitalDiseaseRepository congenitalDiseaseRepository) {
        this.studentHealthRecordRepository = studentHealthRecordRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.drugAllergyHistoryRepository = drugAllergyHistoryRepository;
        this.congenitalDiseaseRepository = congenitalDiseaseRepository;
    }

    @GetMapping("/StudentHealthRecord")
    public Collection<StudentHealthRecord> StudentHealthRecords() {
        return studentHealthRecordRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("StudentHealthRecord/{studentproId}/{weight}/{heigth}/{congenitaldiseaseid}/{drugallergyid}")
    public StudentHealthRecord newStudentHealthRecord(@PathVariable long studentproId,
            @PathVariable long drugallergyid, @PathVariable long congenitaldiseaseid, @PathVariable Integer weight,
            @PathVariable Integer heigth) {
        StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
        studentHealthRecord.setWeight(weight);
        studentHealthRecord.setHeight(heigth);

        StudentProfile studentprofiles = studentProfileRepository.findById(studentproId);
        studentHealthRecord.setStudentprofile(studentprofiles);

        CongenitalDisease congenitaldisease = congenitalDiseaseRepository.findById(congenitaldiseaseid);
        studentHealthRecord.setCongenitaldisease(congenitaldisease);

        DrugAllergyHistory drugAllergyHistory = drugAllergyHistoryRepository.findById(drugallergyid);
        studentHealthRecord.setDrugallergyhistory(drugAllergyHistory);
        return studentHealthRecordRepository.save(studentHealthRecord);

    }
}
