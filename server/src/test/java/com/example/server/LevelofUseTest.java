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

import com.example.server.FinancialInfo.entity.LevelofUse;
import com.example.server.FinancialInfo.repository.LevelofUseRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
 
@DataJpaTest
public class LevelofUseTest {

    private Validator validator;

    @Autowired
    LevelofUseRepository levelofuseRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5812431_testCreateLevelofUseOK() {
		LevelofUse levelofuse = new LevelofUse();
		levelofuse.setLevelId(1L);
		levelofuse.setUsed("พอใช้");
		levelofuse = levelofuseRepository.saveAndFlush(levelofuse);

		Optional<LevelofUse> levelofuseCreated = levelofuseRepository.findById(levelofuse.getLevelId());
        assertEquals(1L, levelofuseCreated.get().getLevelId());
        assertEquals("พอใช้", levelofuseCreated.get().getUsed());
    }

	@Test
    void B5812431_testLevelIdMustNotBeNull() {
        LevelofUse levelofuse = new LevelofUse();
		levelofuse.setLevelId(null);
		levelofuse.setUsed("พอใช้");

		Set<ConstraintViolation<LevelofUse>> result = validator.validate(levelofuse);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<LevelofUse> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("levelId", v.getPropertyPath().toString());
    }
	
	
	@Test
    void B5812431_testUsedMustNotBeNull() {
        LevelofUse levelofuse = new LevelofUse();
		levelofuse.setLevelId(1L);
		levelofuse.setUsed(null);

		Set<ConstraintViolation<LevelofUse>> result = validator.validate(levelofuse);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<LevelofUse> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("used", v.getPropertyPath().toString());
    }
}