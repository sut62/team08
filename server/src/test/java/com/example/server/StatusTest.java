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

import com.example.server.studentprofile.entity.Status;
import com.example.server.studentprofile.repository.StatusRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class StatusTest {

    private Validator validator;

    @Autowired
    StatusRepository StatusRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5807734_testCreateStatusOK() {
		Status status = new Status();
		status.setStatusId(1L);
		status.setStatus("โสด");
		status = StatusRepository.saveAndFlush(status);

		Optional<Status> StatusCreated = StatusRepository.findById(status.getStatusId());
        assertEquals(1L, StatusCreated.get().getStatusId());
        assertEquals("โสด", StatusCreated.get().getStatus());
    }

	@Test
    void B5807734_testStatusIdMustNotBeNull() {
        Status status = new Status();
		status.setStatusId(null);
		status.setStatus("โสด");

		Set<ConstraintViolation<Status>> result = validator.validate(status);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Status> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("statusId", v.getPropertyPath().toString());
    }
	
	
	@Test
    void B5807734_testStatusMustNotBeNull() {
        Status status = new Status();
		status.setStatusId(4L);
		status.setStatus(null);

		Set<ConstraintViolation<Status>> result = validator.validate(status);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Status> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("status", v.getPropertyPath().toString());
    }
}