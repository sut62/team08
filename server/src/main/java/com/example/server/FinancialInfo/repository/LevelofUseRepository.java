package com.example.server.FinancialInfo.repository;

import com.example.server.FinancialInfo.entity.LevelofUse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LevelofUseRepository extends JpaRepository<LevelofUse, Long> {
    LevelofUse findById(long levelId);
}