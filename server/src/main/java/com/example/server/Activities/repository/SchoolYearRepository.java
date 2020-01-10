package com.example.server.activities.repository;

import com.example.server.activities.entity.SchoolYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:8081")
public interface SchoolYearRepository extends JpaRepository <SchoolYear, Long> {
    SchoolYear findById(long yearid);
}
