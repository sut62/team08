package com.example.server.FinancialInfo.repository;

import com.example.server.FinancialInfo.entity.SpendMoney;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SpendMoneyRepository extends JpaRepository<SpendMoney, Long> {
    SpendMoney findById(long spendId);
}