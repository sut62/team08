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
public class CongenitalDiseaseTest {

    private Validator validator;

    @Autowired
    CongenitalDiseaseRepository CongenitalDiseaseRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5828104_testCreateCongenitalDiseaseOK() {
        CongenitalDisease congenitalDisease = new CongenitalDisease();
        congenitalDisease.setCongenitaldiseaseid(1L);
        congenitalDisease.setCongenitaldisease("โรคภูมิแพ้อากาศ");
        congenitalDisease = CongenitalDiseaseRepository.saveAndFlush(congenitalDisease);

        Optional<CongenitalDisease> CongenitalDisease = CongenitalDiseaseRepository.findById(congenitalDisease.getCongenitaldiseaseid());
        
        assertEquals(1L, CongenitalDisease.get().getCongenitaldiseaseid());
        assertEquals("โรคภูมิแพ้อากาศ", CongenitalDisease.get().getCongenitaldisease());
    }

    @Test 
    void B5828104_testCongenitalDiseaseIdMustNotBeNull() {
        CongenitalDisease congenitalDisease = new CongenitalDisease();
        congenitalDisease.setCongenitaldiseaseid(null);
        congenitalDisease.setCongenitaldisease("โรคภูมิแพ้อากาศ");

        Set<ConstraintViolation<CongenitalDisease>> result = validator.validate(congenitalDisease);

        assertEquals(1, result.size());

        ConstraintViolation<CongenitalDisease> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("congenitaldiseaseid", v.getPropertyPath().toString());
    }


    @Test 
    void B5828104_testCongenitalDiseaseMustNotBeNull() {
        CongenitalDisease congenitalDisease = new CongenitalDisease();
        congenitalDisease.setCongenitaldiseaseid(1L);
        congenitalDisease.setCongenitaldisease(null);

        Set<ConstraintViolation<CongenitalDisease>> result = validator.validate(congenitalDisease);

        assertEquals(1, result.size());

        ConstraintViolation<CongenitalDisease> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("congenitaldisease", v.getPropertyPath().toString());
    }

    @Test 
    void B5828104_testCongenitalDiseaseMustNotLessThan5() {
        CongenitalDisease congenitalDisease = new CongenitalDisease();
        congenitalDisease.setCongenitaldiseaseid(1L);
        congenitalDisease.setCongenitaldisease("โรค");

        Set<ConstraintViolation<CongenitalDisease>> result = validator.validate(congenitalDisease);

        assertEquals(1, result.size());

        ConstraintViolation<CongenitalDisease> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("congenitaldisease", v.getPropertyPath().toString());
    }

    @Test 
    void B5828104_testCongenitalDiseaseMustNotMoreThan30() {
        CongenitalDisease congenitalDisease = new CongenitalDisease();
        congenitalDisease.setCongenitaldiseaseid(1L);
        congenitalDisease.setCongenitaldisease("โรคภูมิแพ้อากาศเป็นพิษเรื้อรัง ไม่สามารถรักษาให้หายขาดได้");

        Set<ConstraintViolation<CongenitalDisease>> result = validator.validate(congenitalDisease);

        assertEquals(1, result.size());

        ConstraintViolation<CongenitalDisease> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("congenitaldisease", v.getPropertyPath().toString());
    }
}