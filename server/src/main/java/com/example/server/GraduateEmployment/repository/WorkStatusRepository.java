package com.example.server.GraduateEmployment.repository;

import com.example.server.GraduateEmployment.entity.WorkStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:8081")
public interface WorkStatusRepository extends JpaRepository <WorkStatus, Long> {
    WorkStatus findById(long workstatusId);
}