package com.example.server.User.repository;

import com.example.server.User.entity.UserType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserTypeRepository extends JpaRepository <UserType, Long> {
    UserType findById(long ruleId);
}