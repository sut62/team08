package com.example.server.Discipline.repository;

import com.example.server.Discipline.entity.Discipline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DisciplineRepository extends JpaRepository <Discipline, Long> {
    Discipline findById(long disciplineId);
}