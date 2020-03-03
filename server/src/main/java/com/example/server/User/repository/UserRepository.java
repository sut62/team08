package com.example.server.User.repository;

import com.example.server.User.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends JpaRepository <User, Long> {
    User findById(long ruleId);
    User findByUsernameAndPassword(String username, String password);
}