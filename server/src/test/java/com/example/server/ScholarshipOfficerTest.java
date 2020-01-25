package com.example.server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.example.server.Scholarship.entity.ScholarshipOfficer;
import com.example.server.Scholarship.repository.ScholarshipOfficerRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class ScholarshipOfficerTest {

    private Validator validator;

    @Autowired
    ScholarshipOfficerRepository ScholarshipOfficerRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5814619_testCreateScholarshipOfficerOK() {
        ScholarshipOfficer scholarshipOfficer = new ScholarshipOfficer();
        scholarshipOfficer.setOfficerid(1L);
        scholarshipOfficer.setOfficername("พนักงาน งานทุน1");
        scholarshipOfficer = ScholarshipOfficerRepository.saveAndFlush(scholarshipOfficer);

        Optional<ScholarshipOfficer> scholarshipOfficerCreated = ScholarshipOfficerRepository.findById(scholarshipOfficer.getOfficerid());
        assertEquals(1L, scholarshipOfficerCreated.get().getOfficerid());
        assertEquals("พนักงาน งานทุน1", scholarshipOfficerCreated.get().getOfficername());
    }

    @Test 
    void B5814619_testOfficeridMustNotBeNull() {
        ScholarshipOfficer scholarshipOfficer = new ScholarshipOfficer();
        scholarshipOfficer.setOfficerid(null);
        scholarshipOfficer.setOfficername("พนักงาน งานทุน1");

        Set<ConstraintViolation<ScholarshipOfficer>> result = validator.validate(scholarshipOfficer);

        assertEquals(1, result.size());

        ConstraintViolation<ScholarshipOfficer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("officerid", v.getPropertyPath().toString());
    }

    @Test 
    void B5815173_testScholarshipOfficerMustNotBeNull() {
        ScholarshipOfficer scholarshipOfficer = new ScholarshipOfficer();
        scholarshipOfficer.setOfficerid(1L);
        scholarshipOfficer.setOfficername(null);

        Set<ConstraintViolation<ScholarshipOfficer>> result = validator.validate(scholarshipOfficer);

        assertEquals(1, result.size());

        ConstraintViolation<ScholarshipOfficer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("officername", v.getPropertyPath().toString());
    }
}