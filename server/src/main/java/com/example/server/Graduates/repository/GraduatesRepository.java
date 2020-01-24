package com.example.server.Graduates.repository;

import com.example.server.Graduates.entity.Graduates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:8081")
public interface GraduatesRepository extends JpaRepository<Graduates, Long> {
    Graduates findById(long graduaId);
}