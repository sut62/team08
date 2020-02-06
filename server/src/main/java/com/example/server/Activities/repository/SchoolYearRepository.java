package com.example.server.Activities.repository;

import com.example.server.Activities.entity.SchoolYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:8080")
public interface SchoolYearRepository extends JpaRepository <SchoolYear, Long> {
    SchoolYear findById(long yearid);
}
