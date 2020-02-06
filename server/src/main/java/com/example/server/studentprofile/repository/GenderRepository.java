package com.example.server.studentprofile.repository;

import com.example.server.studentprofile.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:8080")
public interface GenderRepository extends JpaRepository <Gender, Long>{
    Gender findById(long genderId);
}