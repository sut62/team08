package com.example.server.studentprofile.repository;

import com.example.server.studentprofile.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:8081")
public interface MajorRepository extends JpaRepository <Major, Long> {
    Major findById(long majorId);
}