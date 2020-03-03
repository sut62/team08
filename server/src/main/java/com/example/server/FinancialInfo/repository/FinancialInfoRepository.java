package com.example.server.FinancialInfo.repository;

import com.example.server.FinancialInfo.entity.FinancialInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FinancialInfoRepository extends JpaRepository <FinancialInfo, Long> {
    FinancialInfo findById(long finanId);
}