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

import com.example.server.FinancialInfo.entity.MoneyFormParent;
import com.example.server.FinancialInfo.repository.MoneyFormParentRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
 
@DataJpaTest
public class MoneyFormParentTest {

    private Validator validator;

    @Autowired
    MoneyFormParentRepository moneyformparentRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5812431_testCreateMoneyFormParentOK() {
		MoneyFormParent moneyformparent = new MoneyFormParent();
		moneyformparent.setMoneyparentId(1L);
		moneyformparent.setParent("มารดา");
		moneyformparent = moneyformparentRepository.saveAndFlush(moneyformparent);

		Optional<MoneyFormParent> moneyformparentCreated = moneyformparentRepository.findById(moneyformparent.getMoneyparentId());
        assertEquals(1L, moneyformparentCreated.get().getMoneyparentId());
        assertEquals("มารดา", moneyformparentCreated.get().getParent());
    }

	@Test
    void B5812431_testMoneyparentIdMustNotBeNull() {
        MoneyFormParent moneyformparent = new MoneyFormParent();
		moneyformparent.setMoneyparentId(null);
		moneyformparent.setParent("มารดา");
        
        Set<ConstraintViolation<MoneyFormParent>> result = validator.validate(moneyformparent);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<MoneyFormParent> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("moneyparentId", v.getPropertyPath().toString());
    }
	
	
	@Test
    void B5812431_testUsedMustNotBeNull() {
        MoneyFormParent moneyformparent = new MoneyFormParent();
		moneyformparent.setMoneyparentId(1L);
		moneyformparent.setParent(null);

		Set<ConstraintViolation<MoneyFormParent>> result = validator.validate(moneyformparent);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<MoneyFormParent> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("parent", v.getPropertyPath().toString());
    }
}