package com.example.server.Scholarship.repository;

import com.example.server.Scholarship.entity.Scholarship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ScholarshipRepository extends JpaRepository <Scholarship, Long> {
}