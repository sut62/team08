// package com.example.server.Scholarship.repository;
package com.example.server.Scholarship.repository;

import com.example.server.Scholarship.entity.ScholarshipOfficer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ScholarshipOfficerRepository extends JpaRepository<ScholarshipOfficer, Long> {
        ScholarshipOfficer findById(long officerid);
}