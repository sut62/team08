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

import com.example.server.Activities.entity.SchoolYear;
import com.example.server.Activities.repository.SchoolYearRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class SchoolYearTests {

    private Validator validator;

    @Autowired
    SchoolYearRepository SchoolYearRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5815173_testCreateSchoolYearOK() {
        SchoolYear schoolyear = new SchoolYear();
        schoolyear.setYearid(1L);
        schoolyear.setScyear(2560);
        schoolyear = SchoolYearRepository.saveAndFlush(schoolyear);

        Optional<SchoolYear> schoolyearCreated = SchoolYearRepository.findById(schoolyear.getYearid());
        assertEquals(1L, schoolyearCreated.get().getYearid());
        assertEquals(2560, schoolyearCreated.get().getScyear());
    }

    @Test
    void B5815173_testYearidMustNotBeNull() {
        SchoolYear schoolyear = new SchoolYear();
        schoolyear.setYearid(null);
        schoolyear.setScyear(2560);

        Set<ConstraintViolation<SchoolYear>> result = validator.validate(schoolyear);

        assertEquals(1, result.size());

        ConstraintViolation<SchoolYear> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("yearid", v.getPropertyPath().toString());
    }


    @Test
    void B5815173_testSchoolYearMustNotBeNull() {
        SchoolYear schoolyear = new SchoolYear();
        schoolyear.setYearid(1L);
        schoolyear.setScyear(null);

        Set<ConstraintViolation<SchoolYear>> result = validator.validate(schoolyear);

        assertEquals(1, result.size());

        ConstraintViolation<SchoolYear> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("scyear", v.getPropertyPath().toString());
    }
}