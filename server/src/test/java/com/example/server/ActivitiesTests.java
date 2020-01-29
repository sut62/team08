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

import com.example.server.Activities.entity.Activities;
import com.example.server.Activities.repository.ActivitiesRepository;
import com.example.server.studentprofile.entity.StudentProfile;

import java.util.Optional;
import java.util.Set;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class ActivitiesTests {

	private Validator validator;

	@Autowired
	private ActivitiesRepository activitiesRepository;

	@BeforeEach
	public void setup() {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5815173_testCreatedActivitiesOK() {
        Activities activities = new Activities();
        final Date date = new Date();
        activities.setActid(1L);
        activities.setActname("กกกกก");
        activities.setTel("0812345678");
        activities.setDate(date);

        activities = activitiesRepository.saveAndFlush(activities);

        Optional<Activities> ActivitiesCreated = activitiesRepository
                .findById(activities.getActid());
        assertEquals(1L, ActivitiesCreated.get().getActid());
        assertEquals(date, ActivitiesCreated.get().getDate());
        assertEquals("กกกกก", ActivitiesCreated.get().getActname());
        assertEquals("0812345678", ActivitiesCreated.get().getTel());
    }

    // Actname กรณีที่ 2 น้อยกว่า 5 ตัว
    @Test
    void B5815173_testActnameSizeLessThan5() {
        final Activities activities = new Activities();
        final Date date = new Date();
        activities.setActid(1L);
        activities.setActname("กกก");
        activities.setTel("0812345678");
        activities.setDate(date);

        final Set<ConstraintViolation<Activities>> result = validator.validate(activities);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Activities> v = result.iterator().next();
        assertEquals("size must be between 5 and 20", v.getMessage());
        assertEquals("actname", v.getPropertyPath().toString());
    }

    // Actname กรณีที่ 3 มากกว่า 20 ตัว
    @Test
    void B5815173_testActnameSizeMorethan20() {
        final Activities activities = new Activities();
        final Date date = new Date();
        activities.setActid(1L);
        activities.setActname("กกกกกกกกกกกกกกกกกกกกก");
        activities.setTel("0812345678");
        activities.setDate(date);

        final Set<ConstraintViolation<Activities>> result = validator.validate(activities);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Activities> v = result.iterator().next();
        assertEquals("size must be between 5 and 20", v.getMessage());
        assertEquals("actname", v.getPropertyPath().toString());
    }

    // Actname กรณีที่ 4 ชื่อต้องไม่เป็นค่าว่าง
    @Test
    void B5815173_testActnameMustNotBeNull() {
        final Activities activities = new Activities();
        final Date date = new Date();
        activities.setActid(1L);
        activities.setActname(null);
        activities.setTel("0812345678");
        activities.setDate(date);

        final Set<ConstraintViolation<Activities>> result = validator.validate(activities);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Activities> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("actname", v.getPropertyPath().toString());
    }

    // Tel กรณีที่ 1 มากกว่า 10 ตัว
    @Test
    void B5815173_testTelNotBe11Digit() {
        final Activities activities = new Activities();
        final Date date = new Date();
        activities.setActid(1L);
        activities.setActname("กกกกก");
        activities.setTel("08123456789");
        activities.setDate(date);

        final Set<ConstraintViolation<Activities>> result = validator.validate(activities);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Activities> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }

    // Tel กรณีที่ 2 น้อยกว่า 10 ตัว
    @Test
    void B5815173_testTelNotBe9Digit() {
        final Activities activities = new Activities();
        final Date date = new Date();
        activities.setActid(1L);
        activities.setActname("กกกกก");
        activities.setTel("081234567");
        activities.setDate(date);

        final Set<ConstraintViolation<Activities>> result = validator.validate(activities);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Activities> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }

    @Test
    void B5815173_testTelNotBeCharactor() {
        final Activities activities = new Activities();
        final Date date = new Date();
        activities.setActid(1L);
        activities.setActname("กกกกก");
        activities.setTel("081234567A");
        activities.setDate(date);

        final Set<ConstraintViolation<Activities>> result = validator.validate(activities);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Activities> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }

    // Tel กรณีที่ 4 ชื่อต้องไม่เป็นค่าว่าง
    @Test
    void B5815173_testTelMustNotBeNull() {
        final Activities activities = new Activities();
        final Date date = new Date();
        activities.setActid(1L);
        activities.setActname("กกกกก");
        activities.setTel(null);
        activities.setDate(date);

        final Set<ConstraintViolation<Activities>> result = validator.validate(activities);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Activities> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }

    // Date กรณีที่ 1 ต้องไม่เป็นค่าว่าง
    @Test
    void B5815173_testDateMustNotBeNull() {
        final Activities activities = new Activities();
        activities.setActid(1L);
        activities.setActname("กกกกก");
        activities.setTel("0812345678");
        activities.setDate(null);

        final Set<ConstraintViolation<Activities>> result = validator.validate(activities);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Activities> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());
    }

}
