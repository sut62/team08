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

import com.example.server.GraduateEmployment.entity.*;
import com.example.server.GraduateEmployment.repository.*;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class GraduateEmploymentTest {

    private Validator validator;

    @Autowired
    GraduateEmploymentRepository GraduateEmploymentRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCreateGraduateEmploymentOK() {
        GraduateEmployment graduateEmployment = new GraduateEmployment();
        graduateEmployment.setGradempIdId(1L);
        graduateEmployment.setWorkplace("บริษัทเสริมสุข");
        graduateEmployment.setJopposition("วิศกร");
        graduateEmployment.setEmail("Test_email@gmail.com");
        graduateEmployment = GraduateEmploymentRepository.saveAndFlush(graduateEmployment);

        Optional<GraduateEmployment> GraduateEmploymentCreated = GraduateEmploymentRepository.findById(graduateEmployment.getGradempId());
        assertEquals(1L, GraduateEmploymentCreated.get().getGradempId());
        assertEquals("บริษัทเสริมสุข", GraduateEmploymentCreated.get().getWorkplace());
        assertEquals("วิศกร", GraduateEmploymentCreated.get().getJopposition());
        assertEquals("Test_email@gmail.com", GraduateEmploymentCreated.get().getEmail());
    }

    @Test 
    void testGraduateEmploymentIdMustNotBeNull() {
        GraduateEmployment graduateEmployment = new GraduateEmployment();
        graduateEmployment.setGradempIdId(null);
        graduateEmployment.setWorkplace("บริษัทเสริมสุข");
        graduateEmployment.setJopposition("วิศกร");
        graduateEmployment.setEmail("Test_email@gmail.com");

        Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

        assertEquals(1, result.size());

        ConstraintViolation<GraduateEmployment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("gradempId", v.getPropertyPath().toString());
    }


    @Test 
    void testWorkplaceMustNotBeNull() {
        GraduateEmployment graduateEmployment = new GraduateEmployment();
        graduateEmployment.setGradempIdId(1L);
        graduateEmployment.setWorkplace(null);
        graduateEmployment.setJopposition("วิศกร");
        graduateEmployment.setEmail("Test_email@gmail.com");

        Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

        assertEquals(1, result.size());

        ConstraintViolation<GraduateEmployment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("workplace", v.getPropertyPath().toString());
    }

    @Test 
    void testJobPositionMustNotBeNull() {
        GraduateEmployment graduateEmployment = new GraduateEmployment();
        graduateEmployment.setGradempIdId(1L);
        graduateEmployment.setWorkplace("บริษัทเสริมสุข");
        graduateEmployment.setJopposition(null);
        graduateEmployment.setEmail("Test_email@gmail.com");

        Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

        assertEquals(1, result.size());

        ConstraintViolation<GraduateEmployment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("jopposition", v.getPropertyPath().toString());
    }

    @Test 
    void testEmailMustNotBeNull() {
        GraduateEmployment graduateEmployment = new GraduateEmployment();
        graduateEmployment.setGradempIdId(1L);
        graduateEmployment.setWorkplace("บริษัทเสริมสุข");
        graduateEmployment.setJopposition("วิศกร");
        graduateEmployment.setEmail(null);

        Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

        assertEquals(1, result.size());

        ConstraintViolation<GraduateEmployment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
    }

    @Test 
    void testEmailMustCorrectPattern() {
        GraduateEmployment graduateEmployment = new GraduateEmployment();
        graduateEmployment.setGradempIdId(1L);
        graduateEmployment.setWorkplace("บริษัทเสริมสุข");
        graduateEmployment.setJopposition("วิศกร");
        graduateEmployment.setEmail("Test_#$emial@.com");

        Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

        assertEquals(1, result.size());

        ConstraintViolation<GraduateEmployment> v = result.iterator().next();
        assertEquals("Email pattern should be valid", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
    }

    @Test 
    void testWorkplaceMustNotLessThan5() {
        GraduateEmployment graduateEmployment = new GraduateEmployment();
        graduateEmployment.setGradempIdId(1L);
        graduateEmployment.setWorkplace("สถาน");
        graduateEmployment.setJopposition("วิศกร");
        graduateEmployment.setEmail("Test_email@gmail.com");

        Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

        assertEquals(1, result.size());

        ConstraintViolation<GraduateEmployment> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("workplace", v.getPropertyPath().toString());
    }

    @Test 
    void testWorkplaceMustNotMoreThan30() {
        GraduateEmployment graduateEmployment = new GraduateEmployment();
        graduateEmployment.setGradempIdId(1L);
        graduateEmployment.setWorkplace("สถานที่ทำงานสำหรับนักศึกษาจบใหม่");
        graduateEmployment.setJopposition("วิศกร");
        graduateEmployment.setEmail("Test_email@gmail.com");

        Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

        assertEquals(1, result.size());

        ConstraintViolation<GraduateEmployment> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("workplace", v.getPropertyPath().toString());
    }
}