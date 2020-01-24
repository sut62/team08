package com.example.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.example.server.Discipline.entity.Discipline;
import com.example.server.Discipline.repository.DisciplineRepository;
import com.example.server.studentprofile.entity.StudentProfile;

import java.util.Optional;
import java.util.Set;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class DisciplineTest {

	private Validator validator;

	@Autowired
	private DisciplineRepository disciplineRepository;

	@BeforeEach
	public void setup() {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5801206_testCreatedDisciplineOK() {
        Discipline discipline = new Discipline();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(50L);
        discipline.setSince("term2 2562");
        discipline.setUntil("term3 2562");
        

        discipline = disciplineRepository.saveAndFlush(discipline);

        Optional<Discipline> DisciplineCreated = disciplineRepository
                .findById(discipline.getDisciplineId());
        assertEquals(1L, DisciplineCreated.get().getDisciplineId());
        assertEquals("2562", DisciplineCreated.get().getSchoolyear());
        assertEquals(50L, DisciplineCreated.get().getPoint());
        assertEquals("term2 2562", DisciplineCreated.get().getSince());
        assertEquals("term3 2562", DisciplineCreated.get().getUntil());
    }

    // Schoolyear กรณีที่ 2 น้อยกว่า 4 ตัว
    @Test
    void B5801206_testSchoolyearSizeLessThan4() {
        Discipline discipline = new Discipline();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("123");
        discipline.setPoint(50L);
        discipline.setSince("term2 2562");
        discipline.setUntil("term3 2562");

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must match \"\\d{4}\"", v.getMessage());
        assertEquals("schoolyear", v.getPropertyPath().toString());
    }

    // Schoolyear กรณีที่ 3 มากกว่า 4 ตัว
    @Test
    void B5801206_testSchoolyearSizeMorethan4() {
        Discipline discipline = new Discipline();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("12345");
        discipline.setPoint(50L);
        discipline.setSince("term2 2562");
        discipline.setUntil("term3 2562");

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must match \"\\d{4}\"", v.getMessage());
        assertEquals("schoolyear", v.getPropertyPath().toString());
    }

    @Test // Schoolyear กรณี 4 ต้องไม่เป็น Charactor
    void B5801206_testSchoolyearNotBeCharactor() {
        Discipline discipline = new Discipline();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("123A");
        discipline.setPoint(50L);
        discipline.setSince("term2 2562");
        discipline.setUntil("term3 2562");

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        assertEquals(1, result.size());

        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must match \"\\d{4}\"", v.getMessage());
        assertEquals("schoolyear", v.getPropertyPath().toString());
    }

    // Schoolyear กรณีที่ 5 ต้องไม่เป็นค่าว่าง
    @Test
    void B5801206_testSchoolyearMustNotBeNull() {
        Discipline discipline = new Discipline();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear(null);
        discipline.setPoint(50L);
        discipline.setSince("term2 2562");
        discipline.setUntil("term3 2562");

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("schoolyear", v.getPropertyPath().toString());
    }

    // Since กรณีที่ 6 น้อยกว่า 4 ตัว
    @Test
    void B5801206_testSinceMustNotLessThan4() {
        Discipline discipline = new Discipline();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(50L);
        discipline.setSince("123");
        discipline.setUntil("term3 2562");

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        assertEquals(1, result.size());

        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("size must be between 4 and 10", v.getMessage());
        assertEquals("since", v.getPropertyPath().toString());
    }

    // Since กรณีที่ 7 มากกว่า 10 ตัว
    @Test
    void B5801206_testSinceMustNotMoreThan10() {
        Discipline discipline = new Discipline();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(50L);
        discipline.setSince("12345678901");
        discipline.setUntil("term3 2562");

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        assertEquals(1, result.size());

        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("size must be between 4 and 10", v.getMessage());
        assertEquals("since", v.getPropertyPath().toString());
    }

    // Since กรณีที่ 8 ต้องไม่เป็นค่าว่าง
    @Test
    void B5801206_testSinceMustNotBeNull() {
        Discipline discipline = new Discipline();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(50L);
        discipline.setSince(null);
        discipline.setUntil("term3 2562");

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("since", v.getPropertyPath().toString());
    }

    // Until กรณีที่ 9 น้อยกว่า 4 ตัว
    @Test
    void B5801206_testUntilMustNotLessThan4() {
        Discipline discipline = new Discipline();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(50L);
        discipline.setSince("term2 2562");
        discipline.setUntil("123");

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        assertEquals(1, result.size());

        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("size must be between 4 and 10", v.getMessage());
        assertEquals("until", v.getPropertyPath().toString());
    }

    // Until กรณีที่ 10 มากกว่า 10 ตัว
    @Test
    void B5801206_testUntilMustNotMoreThan10() {
        Discipline discipline = new Discipline();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(50L);
        discipline.setSince("term2 2562");
        discipline.setUntil("12345678901");

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        assertEquals(1, result.size());

        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("size must be between 4 and 10", v.getMessage());
        assertEquals("until", v.getPropertyPath().toString());
    }

    // Until กรณีที่ 11 ต้องไม่เป็นค่าว่าง
    @Test
    void B5801206_testUntilMustNotBeNull() {
        Discipline discipline = new Discipline();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(50L);
        discipline.setSince("term2 2562");
        discipline.setUntil(null);

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("until", v.getPropertyPath().toString());
    }

    // Point กรณีที่ 12 ต้องไม่เป็นค่าว่าง
    @Test
    void B5801206_testPointMustNotBeNull() {
        Discipline discipline = new Discipline();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(null);
        discipline.setSince("term2 2562");
        discipline.setUntil("term3 2562");

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("point", v.getPropertyPath().toString());
    }

}
