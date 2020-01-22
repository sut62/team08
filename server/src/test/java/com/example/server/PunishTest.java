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

import com.example.server.Discipline.entity.Punish;
import com.example.server.Discipline.repository.PunishRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class PunishTest {

    private Validator validator;

    @Autowired
    PunishRepository punishRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test //กรณี 1 Create Punish OK
    void B5801206_testCreatePunishOK() {
        Punish punish = new Punish();
        punish.setPunishId(1L);
        punish.setPunishType("ตักเตือนเป็นลายลักษณ์อักษร");
        punish = punishRepository.saveAndFlush(punish);

        Optional<Punish> punishCreated = punishRepository.findById(punish.getPunishId());
        assertEquals(1L, punishCreated.get().getPunishId());
        assertEquals("ตักเตือนเป็นลายลักษณ์อักษร", punishCreated.get().getPunishType());
    }

    @Test //กรณี 2 PunishId must be NotNull
    void B5801206_testPunishIdMustNotBeNull() {
        Punish punish = new Punish();
        punish.setPunishId(null);
        punish.setPunishType("ตักเตือนเป็นลายลักษณ์อักษร");

        Set<ConstraintViolation<Punish>> result = validator.validate(punish);

        assertEquals(1, result.size());

        ConstraintViolation<Punish> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("punishId", v.getPropertyPath().toString());
    }


    @Test // กรณี 3 PunishType must be NotNull
    void B5801206_testPunishTypeMustNotBeNull() {
        Punish punish = new Punish();
        punish.setPunishId(1L);
        punish.setPunishType(null);

        Set<ConstraintViolation<Punish>> result = validator.validate(punish);

        assertEquals(1, result.size());

        ConstraintViolation<Punish> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("punishType", v.getPropertyPath().toString());
    }
}