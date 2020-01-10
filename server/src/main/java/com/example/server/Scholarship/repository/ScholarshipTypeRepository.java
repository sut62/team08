package com.example.server.Scholarship.repository;

import com.example.server.Scholarship.entity.ScholarshipType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ScholarshipTypeRepository extends JpaRepository <ScholarshipType, Long> {
        ScholarshipType findById(long scholarshipTypeid);
}