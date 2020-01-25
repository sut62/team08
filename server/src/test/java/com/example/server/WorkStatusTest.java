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
public class WorkStatusTest {

    private Validator validator;

    @Autowired
    WorkStatusRepository WorkStatusRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test 
    void testCreateWorkStatusOK() {
        WorkStatus workStatus = new WorkStatus();
        workStatus.setWorkstatusId(1L);
        workStatus.setStatus("ทำงานแล้ว");
        workStatus = WorkStatusRepository.saveAndFlush(workStatus);

        Optional<WorkStatus> WorkStatusCreated = WorkStatusRepository.findById(workStatus.getWorkstatusId());
        assertEquals(1L, WorkStatusCreated.get().getWorkstatusId());
        assertEquals("ทำงานแล้ว", WorkStatusCreated.get().getStatus());
    }

    @Test 
    void testWorkStatusIdMustNotBeNull() {
        WorkStatus workStatus = new WorkStatus();
        workStatus.setWorkstatusId(null);
        workStatus.setStatus("ทำงานแล้ว");

        Set<ConstraintViolation<WorkStatus>> result = validator.validate(workStatus);

        assertEquals(1, result.size());

        ConstraintViolation<WorkStatus> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("workstatusId", v.getPropertyPath().toString());
    }


    @Test 
    void testStatusMustNotBeNull() {
        WorkStatus workStatus = new WorkStatus();
        workStatus.setWorkstatusId(1L);
        workStatus.setStatus(null);

        Set<ConstraintViolation<WorkStatus>> result = validator.validate(workStatus);

        assertEquals(1, result.size());

        ConstraintViolation<WorkStatus> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("status", v.getPropertyPath().toString());
    }
}