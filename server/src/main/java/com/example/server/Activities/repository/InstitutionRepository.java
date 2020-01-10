package com.example.server.activities.repository;

import com.example.server.activities.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:8080")
public interface InstitutionRepository extends  JpaRepository <Institution, Long> {
    Institution findById(long insid);
}
