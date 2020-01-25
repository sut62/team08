package com.example.server.StudentHealthRecord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.server.StudentHealthRecord.entity.DrugAllergyHistory;
import com.example.server.StudentHealthRecord.repository.DrugAllergyHistoryRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class DrugAllergyHistoryController {

    @Autowired
    private DrugAllergyHistoryRepository drugAllergyHistoryRepository;

    public DrugAllergyHistoryController(DrugAllergyHistoryRepository drugAllergyHistoryRepository) {
        this.drugAllergyHistoryRepository = drugAllergyHistoryRepository;
    }
    @GetMapping("/drugallergyhistory")
    public Collection<DrugAllergyHistory> DrugAllergyHistorys(){
        return drugAllergyHistoryRepository.findAll().stream().collect(Collectors.toList())  ;
    
    }
    
}
