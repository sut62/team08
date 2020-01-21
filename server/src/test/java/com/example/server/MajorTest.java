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

import com.example.server.studentprofile.entity.Major;
import com.example.server.studentprofile.repository.MajorRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class MajorTest {

    private Validator validator;

    @Autowired
    MajorRepository MajorRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5807734_testCreateMajorOK() {
		Major major = new Major();
		major.setMajorId(1L);
		major.setMajor("​​Engineering");
		major = MajorRepository.saveAndFlush(major);

		Optional<Major> majorCreated = MajorRepository.findById(major.getMajorId());
        assertEquals(1L, majorCreated.get().getMajorId());
        assertEquals("​​Engineering", majorCreated.get().getMajor());
    }

	@Test
    void B5807734_testMajorIdMustNotBeNull() {
        Major major = new Major();
		major.setMajorId(null);
		major.setMajor("​​Engineering");

		Set<ConstraintViolation<Major>> result = validator.validate(major);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Major> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("majorId", v.getPropertyPath().toString());
    }
	
	
	@Test
    void B5807734_testMajorMustNotBeNull() {
        Major major = new Major();
		major.setMajorId(1L);
		major.setMajor(null);

		Set<ConstraintViolation<Major>> result = validator.validate(major);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Major> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("major", v.getPropertyPath().toString());
    }
}