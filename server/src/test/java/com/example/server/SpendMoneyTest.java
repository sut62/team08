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

import com.example.server.FinancialInfo.entity.SpendMoney;
import com.example.server.FinancialInfo.repository.SpendMoneyRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
 
@DataJpaTest
public class SpendMoneyTest {

    private Validator validator;

    @Autowired
    SpendMoneyRepository spendmoneyRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5812431_testCreateSpendMoneyOK() {
		SpendMoney spendmoney = new SpendMoney();
		spendmoney.setSpendId(1L);
		spendmoney.setUsemoney("2000-3000");
		spendmoney = spendmoneyRepository.saveAndFlush(spendmoney);

		Optional<SpendMoney> spendmoneyCreated = spendmoneyRepository.findById(spendmoney.getSpendId());
        assertEquals(1L, spendmoneyCreated.get().getSpendId());
        assertEquals("2000-3000", spendmoneyCreated.get().getUsemoney());
    }

	@Test
    void B5812431_testSpendIdMustNotBeNull() {
        SpendMoney spendmoney = new SpendMoney();
		spendmoney.setSpendId(null);
		spendmoney.setUsemoney("2000-3000");
        
        Set<ConstraintViolation<SpendMoney>> result = validator.validate(spendmoney);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<SpendMoney> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("spendId", v.getPropertyPath().toString());
    }
	
	
	@Test
    void B5812431_testUsedMustNotBeNull() {
        SpendMoney spendmoney = new SpendMoney();
		spendmoney.setSpendId(1L);
		spendmoney.setUsemoney(null);

		Set<ConstraintViolation<SpendMoney>> result = validator.validate(spendmoney);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<SpendMoney> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("usemoney", v.getPropertyPath().toString());
    }
}