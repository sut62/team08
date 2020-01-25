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

import com.example.server.Scholarship.entity.ScholarshipType;
import com.example.server.Scholarship.repository.ScholarshipTypeRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class ScholarshipTypeTest {

    private Validator validator;

    @Autowired
    ScholarshipTypeRepository ScholarshipTypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5814619_testCreateScholarshipTypeOK() {
        ScholarshipType scholarshipType = new ScholarshipType();
        scholarshipType.setScholarshipTypeid(1L);
        scholarshipType.setGoodEducation("ทุนศักยบันณฑิต");
        scholarshipType.setEducationalLoans("ทุนกู้ยืมการศึกษา");
        scholarshipType = ScholarshipTypeRepository.saveAndFlush(scholarshipType);

        Optional<ScholarshipType> scholarshipTypeCreated = ScholarshipTypeRepository.findById(scholarshipType.getScholarshipTypeid());
        assertEquals(1L, scholarshipTypeCreated.get().getScholarshipTypeid());
        assertEquals("ทุนศักยบันณฑิต", scholarshipTypeCreated.get().getGoodEducation());
        assertEquals("ทุนกู้ยืมการศึกษา", scholarshipTypeCreated.get().getEducationalLoans());
    }

    @Test
    void B5815173_testScholarshipTypeidMustNotBeNull() {
        ScholarshipType scholarshipType = new ScholarshipType();
        scholarshipType.setScholarshipTypeid(null);
        scholarshipType.setGoodEducation("ทุนศักยบันณฑิต");
        scholarshipType.setEducationalLoans("ทุนกู้ยืมการศึกษา");

        Set<ConstraintViolation<ScholarshipType>> result = validator.validate(scholarshipType);

        assertEquals(1, result.size());

        ConstraintViolation<ScholarshipType> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ScholarshipTypeid", v.getPropertyPath().toString());
    }


    @Test
    void B5814619_testGoodEducationMustNotBeNull() {
        ScholarshipType scholarshipType = new ScholarshipType();
        scholarshipType.setScholarshipTypeid(1L);
        scholarshipType.setGoodEducation(null);
        scholarshipType.setEducationalLoans("ทุนกู้ยืมการศึกษา");

        Set<ConstraintViolation<ScholarshipType>> result = validator.validate(scholarshipType);

        assertEquals(1, result.size());

        ConstraintViolation<ScholarshipType> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("goodEducation", v.getPropertyPath().toString());
    }

    @Test
    void B5814619_testEducationalLoansMustNotBeNull() {
        ScholarshipType scholarshipType = new ScholarshipType();
        scholarshipType.setScholarshipTypeid(1L);
        scholarshipType.setGoodEducation("ทุนศักยบันณฑิต");
        scholarshipType.setEducationalLoans(null);

        Set<ConstraintViolation<ScholarshipType>> result = validator.validate(scholarshipType);

        assertEquals(1, result.size());

        ConstraintViolation<ScholarshipType> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("educationalLoans", v.getPropertyPath().toString());
    }
}