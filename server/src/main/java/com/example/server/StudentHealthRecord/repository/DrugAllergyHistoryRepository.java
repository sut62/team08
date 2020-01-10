package com.example.server.StudentHealthRecord.repository;

import com.example.server.StudentHealthRecord.entity.DrugAllergyHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DrugAllergyHistoryRepository extends  JpaRepository <DrugAllergyHistory, Long> {
    DrugAllergyHistory findById(long drugallergyid);
}
