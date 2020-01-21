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

import com.example.server.studentprofile.entity.Gender;
import com.example.server.studentprofile.repository.GenderRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class GenderTest {

    private Validator validator;

    @Autowired
    GenderRepository genderRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5807734_testCreateGenderOK() {
		Gender gender = new Gender();
		gender.setGenderId(1L);
		gender.setGender("นาย");
		gender = genderRepository.saveAndFlush(gender);

		Optional<Gender> genderCreated = genderRepository.findById(gender.getGenderId());
        assertEquals(1L, genderCreated.get().getGenderId());
        assertEquals("นาย", genderCreated.get().getGender());
    }

	@Test
    void B5807734_testGenderIdMustNotBeNull() {
        Gender gender = new Gender();
		gender.setGenderId(null);
		gender.setGender("นาย");

		Set<ConstraintViolation<Gender>> result = validator.validate(gender);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Gender> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("genderId", v.getPropertyPath().toString());
    }
	
	
	@Test
    void B5807734_testGenderMustNotBeNull() {
        Gender gender = new Gender();
		gender.setGenderId(1L);
		gender.setGender(null);

		Set<ConstraintViolation<Gender>> result = validator.validate(gender);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Gender> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("gender", v.getPropertyPath().toString());
    }
}