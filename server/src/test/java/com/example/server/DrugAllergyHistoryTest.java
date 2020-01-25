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
public class DrugAllergyHistoryTest {

    private Validator validator;

    @Autowired
    DrugAllergyHistoryRepository DrugAllergyHistoryRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCreateDrugAllergyHistoryOK() {
        DrugAllergyHistory drugAllergyHistory = new DrugAllergyHistory();
        drugAllergyHistory.setDrugallergyhistoryid(1L);
        drugAllergyHistory.setDrugallergyhistory("ยาคลายกล้ามเนื้อ");
        drugAllergyHistory = DrugAllergyHistoryRepository.saveAndFlush(drugAllergyHistory);
        Optional<DrugAllergyHistory> DrugAllergyHistory = DrugAllergyHistoryRepository.findById(drugAllergyHistory.getDrugallergyhistoryid());
        assertEquals(1L, DrugAllergyHistory.get().getDrugallergyhistoryid());
        assertEquals("ยาคลายกล้ามเนื้อ", DrugAllergyHistory.get().getDrugallergyhistory());
    }

    @Test 
    void testDrugAllergyHistoryIdMustNotBeNull() {
        DrugAllergyHistory drugAllergyHistory = new DrugAllergyHistory();
        drugAllergyHistory.setDrugallergyhistoryid(null);
        drugAllergyHistory.setDrugallergyhistory("ยาคลายกล้ามเนื้อ");

        Set<ConstraintViolation<DrugAllergyHistory>> result = validator.validate(drugAllergyHistory);

        assertEquals(1, result.size());

        ConstraintViolation<DrugAllergyHistory> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("drugallergyhistoryid", v.getPropertyPath().toString());
    }


    @Test 
    void testDrugAllergyHistoryMustNotBeNull() {
        DrugAllergyHistory drugAllergyHistory = new DrugAllergyHistory();
        drugAllergyHistory.setDrugallergyhistoryid(1L);
        drugAllergyHistory.setDrugallergyhistory(null);

        Set<ConstraintViolation<DrugAllergyHistory>> result = validator.validate(drugAllergyHistory);

        assertEquals(1, result.size());

        ConstraintViolation<DrugAllergyHistory> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("drugallergyhistory", v.getPropertyPath().toString());
    }
}