// package com.example.server.Scholarship.controller;
package com.example.server.Scholarship.controller;
import com.example.server.Scholarship.entity.ScholarshipType;
import com.example.server.Scholarship.repository.ScholarshipTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ScholarshipTypeController {

    @Autowired
    private ScholarshipTypeRepository scholarshipTypeRepository;

    public ScholarshipTypeController(ScholarshipTypeRepository scholarshipTypeRepository) {
        this.scholarshipTypeRepository = scholarshipTypeRepository;
    }
    @GetMapping("/scholarshiptypes")
    public Collection<ScholarshipType> ScholarshipTypes() {
        return scholarshipTypeRepository.findAll().stream().collect(Collectors.toList());
    }
}