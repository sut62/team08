package com.example.server.Activities.repository;

import com.example.server.Activities.entity.Activities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:8081")
public interface ActivitiesRepository extends JpaRepository <Activities, Long> {
    Activities findById(long actid);

}
