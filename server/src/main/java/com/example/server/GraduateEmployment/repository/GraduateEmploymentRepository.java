package com.example.server.GraduateEmployment.repository;

import com.example.server.GraduateEmployment.entity.GraduateEmployment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:8081")
public interface GraduateEmploymentRepository extends JpaRepository <GraduateEmployment, Long> {
    GraduateEmployment findById(long gradempId);
}