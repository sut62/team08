package com.example.server.Discipline.repository;

import com.example.server.Discipline.entity.Punish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PunishRepository extends JpaRepository <Punish, Long> {
    Punish findById(long punishId);
}