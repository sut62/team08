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

import com.example.server.StudentHealthRecord.entity.*;
import com.example.server.StudentHealthRecord.repository.*;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class StudentHealthRecordTest {

    private Validator validator;

    @Autowired
    StudentHealthRecordRepository StudentHealthRecordRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test 
    void B5828104_testCreateStudentHealthRecordOK() {
        StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
        studentHealthRecord.setStudentHealthRecordid(1L);
        studentHealthRecord.setHeight(180);
        studentHealthRecord.setWeight(60);
        studentHealthRecord = StudentHealthRecordRepository.saveAndFlush(studentHealthRecord);

        Optional<StudentHealthRecord> Result = StudentHealthRecordRepository.findById(studentHealthRecord.getStudentHealthRecordid());
        assertEquals(1L, Result.get().getStudentHealthRecordid());
        assertEquals(180, Result.get().getHeight());
        assertEquals(60, Result.get().getWeight());
    }

    @Test 
    void B5828104_testStudentHealthRecordIdMustNotBeNull() {
        StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
        studentHealthRecord.setStudentHealthRecordid(null);
        studentHealthRecord.setHeight(180);
        studentHealthRecord.setWeight(60);

        Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);

        assertEquals(1, result.size());

        ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("StudentHealthRecordid", v.getPropertyPath().toString());
    }


    @Test 
    void B5828104_testHeightMustNotBeNull() {
        StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
        studentHealthRecord.setStudentHealthRecordid(1L);
        studentHealthRecord.setHeight(null);
        studentHealthRecord.setWeight(60);

        Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);

        assertEquals(1, result.size());

        ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("height", v.getPropertyPath().toString());
    }

    @Test 
    void B5828104_testWidthMustNotBeNull() {
        StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
        studentHealthRecord.setStudentHealthRecordid(1L);
        studentHealthRecord.setHeight(180);
        studentHealthRecord.setWeight(null);

        Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);

        assertEquals(1, result.size());

        ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("weight", v.getPropertyPath().toString());
    }

    @Test 
    void B5828104_testHeightMustNotLessThan100() {
        StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
        studentHealthRecord.setStudentHealthRecordid(1L);
        studentHealthRecord.setHeight(99);
        studentHealthRecord.setWeight(60);

        Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);

        assertEquals(1, result.size());

        ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
        assertEquals("must be greater than or equal to 100", v.getMessage());
        assertEquals("height", v.getPropertyPath().toString());
    }

    @Test 
    void B5828104_testHeightMustNotMoreThan220() {
        StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
        studentHealthRecord.setStudentHealthRecordid(1L);
        studentHealthRecord.setHeight(221);
        studentHealthRecord.setWeight(60);

        Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);

        assertEquals(1, result.size());

        ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
        assertEquals("must be less than or equal to 220", v.getMessage());
        assertEquals("height", v.getPropertyPath().toString());
    }

    @Test 
    void B5828104_testWeightMustNotLessThan30() {
        StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
        studentHealthRecord.setStudentHealthRecordid(1L);
        studentHealthRecord.setHeight(180);
        studentHealthRecord.setWeight(29);

        Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);

        assertEquals(1, result.size());

        ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
        assertEquals("must be greater than or equal to 30", v.getMessage());
        assertEquals("weight", v.getPropertyPath().toString());
    }

    @Test 
    void B5828104_testWeightMustNotMoreThan200() {
        StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
        studentHealthRecord.setStudentHealthRecordid(1L);
        studentHealthRecord.setHeight(180);
        studentHealthRecord.setWeight(201);

        Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);

        assertEquals(1, result.size());

        ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
        assertEquals("must be less than or equal to 200", v.getMessage());
        assertEquals("weight", v.getPropertyPath().toString());
    }
}