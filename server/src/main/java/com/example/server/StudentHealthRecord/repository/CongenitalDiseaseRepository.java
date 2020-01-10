package com.example.server.StudentHealthRecord.repository;

import com.example.server.StudentHealthRecord.entity.CongenitalDisease;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CongenitalDiseaseRepository extends  JpaRepository <CongenitalDisease, Long> {
    CongenitalDisease findById(long condisid);
}
