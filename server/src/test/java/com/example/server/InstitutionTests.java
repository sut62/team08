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

import com.example.server.Activities.entity.Institution;
import com.example.server.Activities.repository.InstitutionRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class InstitutionTests {

    private Validator validator;

    @Autowired
    InstitutionRepository institutionRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5815173_testCreateInstitutionOK() {
        Institution institution = new Institution();
        institution.setInsid(1L);
        institution.setInsname("ชมรมคอมพิวเตอร์");
        institution = institutionRepository.saveAndFlush(institution);

        Optional<Institution> institutionCreated = institutionRepository.findById(institution.getInsid());
        assertEquals(1L, institutionCreated.get().getInsid());
        assertEquals("ชมรมคอมพิวเตอร์", institutionCreated.get().getInsname());
    }

    @Test
    void B5815173_testInsidMustNotBeNull() {
        Institution institution = new Institution();
        institution.setInsid(null);
        institution.setInsname("ชมรมคอมพิวเตอร์");

        Set<ConstraintViolation<Institution>> result = validator.validate(institution);

        assertEquals(1, result.size());

        ConstraintViolation<Institution> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("insid", v.getPropertyPath().toString());
    }


    @Test
    void B5815173_testInstitutionMustNotBeNull() {
        Institution institution = new Institution();
        institution.setInsid(1L);
        institution.setInsname(null);

        Set<ConstraintViolation<Institution>> result = validator.validate(institution);

        assertEquals(1, result.size());

        ConstraintViolation<Institution> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("insname", v.getPropertyPath().toString());
    }
}