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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.server.Scholarship.entity.Scholarship;
import com.example.server.Scholarship.repository.ScholarshipRepository;
import com.example.server.studentprofile.entity.StudentProfile;

import java.util.Optional;
import java.util.Set;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class ScholarshipTest {

    private Validator validator;

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5814619_testCreatedScholarshipOK() {
        Scholarship scholarship = new Scholarship();
        final Date paydate = new Date();
        scholarship.setScholarshipid(1L);
        scholarship.setPaydate(paydate);
        scholarship.setMoney("12345");

        scholarship = scholarshipRepository.saveAndFlush(scholarship);

        Optional<Scholarship> ScholarshipCreated = scholarshipRepository
                .findById(scholarship.getScholarshipid());
        assertEquals(1L, ScholarshipCreated.get().getScholarshipid());
        assertEquals(paydate, ScholarshipCreated.get().getPaydate());
        assertEquals("12345", ScholarshipCreated.get().getMoney());
    }

    // Money กรณีที่ 2 น้อยกว่า 3 ตัว
    @Test
    void B5814619_testMoneySizeLessThan3() {
        final Scholarship scholarship = new Scholarship();
        final Date paydate = new Date();
        scholarship.setScholarshipid(1L);
        scholarship.setPaydate(paydate);
        scholarship.setMoney("01");

        final Set<ConstraintViolation<Scholarship>> result = validator.validate(scholarship);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Scholarship> v = result.iterator().next();
        assertEquals("size must be between 3 and 5", v.getMessage());
        assertEquals("money", v.getPropertyPath().toString());
    }

    // Money กรณีที่ 3 มากกว่า 5 ตัว
    @Test
    void B5814619_testMoneySizeMorethan5() {
        final Scholarship scholarship = new Scholarship();
        final Date paydate = new Date();
        scholarship.setScholarshipid(1L);
        scholarship.setPaydate(paydate);
        scholarship.setMoney("1234567");

        final Set<ConstraintViolation<Scholarship>> result = validator.validate(scholarship);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Scholarship> v = result.iterator().next();
        assertEquals("size must be between 3 and 5", v.getMessage());
        assertEquals("money", v.getPropertyPath().toString());
    }

    // Money กรณีที่ 4 จำนวนเงินต้องไม่เป็นค่าว่าง
    @Test
    void B5814619_testMoneyMustNotBeNull() {
        final Scholarship scholarship = new Scholarship();
        final Date paydate = new Date();
        scholarship.setScholarshipid(1L);
        scholarship.setPaydate(paydate);
        scholarship.setMoney(null);

        final Set<ConstraintViolation<Scholarship>> result = validator.validate(scholarship);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Scholarship> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("money", v.getPropertyPath().toString());
    }

    // Date กรณีที่ 1 ต้องไม่เป็นค่าว่าง
    /*@Test
    void B5814619_testDateMustNotBeNull() {
        final Scholarship scholarship = new Scholarship();
        scholarship.setScholarshipid(1L);
        scholarship.setPaydate(null);
        scholarship.setMoney("123456789");

        final Set<ConstraintViolation<Scholarship>> result = validator.validate(scholarship);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Scholarship> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("paydate", v.getPropertyPath().toString());
    }*/

    @Test
    void B5814619_testDateMustNotBeNull() {
        Scholarship scholarship = new Scholarship();
        scholarship.setScholarshipid(1L);
        scholarship.setPaydate(null);
        scholarship.setMoney("12345");

        Set<ConstraintViolation<Scholarship>> result = validator.validate(scholarship);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Scholarship> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("paydate", v.getPropertyPath().toString());
    }

}
