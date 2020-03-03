package com.example.server.FinancialInfo.repository;

import com.example.server.FinancialInfo.entity.MoneyFormParent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MoneyFormParentRepository extends JpaRepository<MoneyFormParent, Long> {
    MoneyFormParent findById(long moneyparentId);
}