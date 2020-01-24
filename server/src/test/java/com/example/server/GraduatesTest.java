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

import com.example.server.Graduates.entity.Graduates;
import com.example.server.Graduates.repository.GraduatesRepository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class GraduatesTest {

    private Validator validator;

    @Autowired
    GraduatesRepository GraduatesRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5807734_testCreateGraduatesOK() {
        Graduates graduates = new Graduates();
        Date date = new Date();
		graduates.setGraduaId(1L);
        graduates.setHonor("เกียรตินิยม");
        graduates.setGraduadate(date);
        graduates.setGraduation("เข้ารับ");
		graduates = GraduatesRepository.saveAndFlush(graduates);

		Optional<Graduates> GraduatesCreated = GraduatesRepository.findById(graduates.getGraduaId());
        assertEquals(1L, GraduatesCreated.get().getGraduaId());
        assertEquals("เกียรตินิยม", GraduatesCreated.get().getHonor());
        assertEquals(date, GraduatesCreated.get().getGraduadate());
        assertEquals("เข้ารับ", GraduatesCreated.get().getGraduation());
    }

	@Test
    void B5807734_testGraduatesIdMustNotBeNull() {
        Graduates graduates = new Graduates();
        Date date = new Date();
		graduates.setGraduaId(null);
        graduates.setHonor("เกียรตินิยม");
        graduates.setGraduadate(date);
        graduates.setGraduation("เข้ารับ");
		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Graduates> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("graduaId", v.getPropertyPath().toString());
    }
	
	@Test
    void B5807734_testHonorMustNotBeNull() {
        Graduates graduates = new Graduates();
        Date date = new Date();
		graduates.setGraduaId(1L);
        graduates.setHonor(null);
        graduates.setGraduadate(date);
        graduates.setGraduation("เข้ารับ");
		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Graduates> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("honor", v.getPropertyPath().toString());
    }
    @Test
    void B5807734_testGraduateDateMustNotBeNull() {
        Graduates graduates = new Graduates();
		graduates.setGraduaId(1L);
        graduates.setHonor("เกียรตินิยม");
        graduates.setGraduadate(null);
        graduates.setGraduation("เข้ารับ");
		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Graduates> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("graduadate", v.getPropertyPath().toString());
    }
    @Test
    void B5807734_testGraduationMustNotBeNull() {
        Graduates graduates = new Graduates();
        Date date = new Date();
		graduates.setGraduaId(1L);
        graduates.setHonor("เกียรตินิยม");
        graduates.setGraduadate(date);
        graduates.setGraduation(null);
		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Graduates> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("graduation", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testHonorMustNotLessThan5() {
        Graduates graduates = new Graduates();
        Date date = new Date();
		graduates.setGraduaId(1L);
        graduates.setHonor("เกีย");
        graduates.setGraduadate(date);
        graduates.setGraduation("เข้ารับ");
		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Graduates> v = result.iterator().next();
        assertEquals("size must be between 5 and 20", v.getMessage());
        assertEquals("honor", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testHonorMustNotMoreThan20() {
        Graduates graduates = new Graduates();
        Date date = new Date();
		graduates.setGraduaId(1L);
        graduates.setHonor("ได้รับ เกียรตินิยมอันดับ ที่ 2");
        graduates.setGraduadate(date);
        graduates.setGraduation("เข้ารับ");
		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Graduates> v = result.iterator().next();
        assertEquals("size must be between 5 and 20", v.getMessage());
        assertEquals("honor", v.getPropertyPath().toString());
    }

     @Test
    void B5807734_testHonorMustNotBeSpecialCharactor() {
        Graduates graduates = new Graduates();
        Date date = new Date();
		graduates.setGraduaId(1L);
        graduates.setHonor("เกียรตินิยม###");
        graduates.setGraduadate(date);
        graduates.setGraduation("เข้ารับ");
		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Graduates> v = result.iterator().next();
        assertEquals("must match \"^[0-9A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("honor", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testGraduationMustNotLessThan5() {
        Graduates graduates = new Graduates();
        Date date = new Date();
		graduates.setGraduaId(1L);
        graduates.setHonor("เกียรตินิยม");
        graduates.setGraduadate(date);
        graduates.setGraduation("เข้า");
		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Graduates> v = result.iterator().next();
        assertEquals("size must be between 5 and 20", v.getMessage());
        assertEquals("graduation", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testGraduationMustNotMoreThan20() {
        Graduates graduates = new Graduates();
        Date date = new Date();
		graduates.setGraduaId(1L);
        graduates.setHonor("เกียรตินิยม");
        graduates.setGraduadate(date);
        graduates.setGraduation("มีความประสงค์จะเข้ารับ");
		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Graduates> v = result.iterator().next();
        assertEquals("size must be between 5 and 20", v.getMessage());
        assertEquals("graduation", v.getPropertyPath().toString());
    }

     @Test
    void B5807734_testGraduationMustNotBeNumberAndSpecialCharactor() {
        Graduates graduates = new Graduates();
        Date date = new Date();
		graduates.setGraduaId(1L);
        graduates.setHonor("เกียรตินิยม");
        graduates.setGraduadate(date);
        graduates.setGraduation("เข้ารับ 9");
		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Graduates> v = result.iterator().next();
        assertEquals("must match \"^[A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("graduation", v.getPropertyPath().toString());
    }
}