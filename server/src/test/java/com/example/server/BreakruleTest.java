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

import com.example.server.Discipline.entity.Breakrule;
import com.example.server.Discipline.repository.BreakruleRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class BreakruleTest {

    private Validator validator;

    @Autowired
    BreakruleRepository breakruleRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test //กรณี 1 Create Breakrule OK
    void B5801206_testCreateBreakruleOK() {
        Breakrule breakrule = new Breakrule();
        breakrule.setRuleId(1L);
        breakrule.setRuleType("เล่นการพนัน");
        breakrule = breakruleRepository.saveAndFlush(breakrule);

        Optional<Breakrule> breakruleCreated = breakruleRepository.findById(breakrule.getRuleId());
        assertEquals(1L, breakruleCreated.get().getRuleId());
        assertEquals("เล่นการพนัน", breakruleCreated.get().getRuleType());
    }

    @Test //กรณี 2 RuleId must be NotNull
    void B5801206_testRuleIdMustNotBeNull() {
        Breakrule breakrule = new Breakrule();
        breakrule.setRuleId(null);
        breakrule.setRuleType("เล่นการพนัน");

        Set<ConstraintViolation<Breakrule>> result = validator.validate(breakrule);

        assertEquals(1, result.size());

        ConstraintViolation<Breakrule> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ruleId", v.getPropertyPath().toString());
    }


    @Test // กรณี 3 RuleType must be NotNull
    void B5801206_testRuleTypeMustNotBeNull() {
        Breakrule breakrule = new Breakrule();
        breakrule.setRuleId(1L);
        breakrule.setRuleType(null);

        Set<ConstraintViolation<Breakrule>> result = validator.validate(breakrule);

        assertEquals(1, result.size());

        ConstraintViolation<Breakrule> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ruleType", v.getPropertyPath().toString());
    }
}