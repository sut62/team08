package com.example.server.Discipline.repository;

import com.example.server.Discipline.entity.Breakrule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BreakruleRepository extends JpaRepository <Breakrule, Long> {
    Breakrule findById(long ruleId);
}