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

import com.example.server.Graduates.entity.EducationLevel;
import com.example.server.Graduates.repository.EducationLevelRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class EducationLevelTest {

    private Validator validator;

    @Autowired
    EducationLevelRepository EducationLevelRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5807734_testCreateEducationLevelOK() {
		EducationLevel educationLevel = new EducationLevel();
		educationLevel.setEducaId(1L);
		educationLevel.setEducalevel("ปริญญาตรี");
		educationLevel = EducationLevelRepository.saveAndFlush(educationLevel);

		Optional<EducationLevel> EducationLevelCreated = EducationLevelRepository.findById(educationLevel.getEducaId());
        assertEquals(1L, EducationLevelCreated.get().getEducaId());
        assertEquals("ปริญญาตรี", EducationLevelCreated.get().getEducalevel());
    }

	@Test
    void B5807734_testEducationLevelIdMustNotBeNull() {
        EducationLevel educationLevel = new EducationLevel();
		educationLevel.setEducaId(null);
		educationLevel.setEducalevel("ปริญญาตรี");

		Set<ConstraintViolation<EducationLevel>> result = validator.validate(educationLevel);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<EducationLevel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("educaId", v.getPropertyPath().toString());
    }
	
	
	@Test
    void B5807734_testEducationLevelMustNotBeNull() {
        EducationLevel educationLevel = new EducationLevel();
		educationLevel.setEducaId(1L);
		educationLevel.setEducalevel(null);

		Set<ConstraintViolation<EducationLevel>> result = validator.validate(educationLevel);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<EducationLevel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("educalevel", v.getPropertyPath().toString());
    }
}