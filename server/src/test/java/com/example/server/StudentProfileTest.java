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

import com.example.server.studentprofile.entity.StudentProfile;
import com.example.server.studentprofile.repository.StudentProfileRepository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class StudentProfileTest {

    private Validator validator;

    @Autowired
    StudentProfileRepository StudentProfileRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5807734_testCreateStudentProfileOK() {
        StudentProfile studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(1L);
        studentProfile.setNameeng("Test Name");
        studentProfile.setNamethai("ทดสอบ ชื่อ");
        studentProfile.setIdcard("B58XXXXX");
        studentProfile.setIdnumber("1234567890123");
        studentProfile.setAddress("address");
        studentProfile.setAge(22);
        studentProfile.setBrithday(date);
        studentProfile.setBlood("AB");
        studentProfile.setTel("0834567891");        
        studentProfile = StudentProfileRepository.saveAndFlush(studentProfile);

        Optional<StudentProfile> StudentProfileCreated = StudentProfileRepository
                .findById(studentProfile.getStudentproId());
        assertEquals(1L, StudentProfileCreated.get().getStudentproId());
        assertEquals("Test Name", StudentProfileCreated.get().getNameeng());
        assertEquals("ทดสอบ ชื่อ", StudentProfileCreated.get().getNamethai());
        assertEquals("B58XXXXX", StudentProfileCreated.get().getIdcard());
        assertEquals("1234567890123", StudentProfileCreated.get().getIdnumber());
        assertEquals("address", StudentProfileCreated.get().getAddress());
        assertEquals(22, StudentProfileCreated.get().getAge());
        assertEquals(date, StudentProfileCreated.get().getBrithday());
        assertEquals("AB", StudentProfileCreated.get().getBlood());
        assertEquals("0834567891", StudentProfileCreated.get().getTel());
    }

    @Test
    void B5807734_testStudentProIdMustNotBeNull() {
        StudentProfile studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(null);
        studentProfile.setNameeng("Test Name");
        studentProfile.setNamethai("ทดสอบ ชื่อ");
        studentProfile.setIdcard("B58XXXXX");
        studentProfile.setIdnumber("1234567890123");
        studentProfile.setAddress("address");
        studentProfile.setAge(22);
        studentProfile.setBrithday(date);
        studentProfile.setBlood("AB");
        studentProfile.setTel("0834567891");
        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("studentproId", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testIdNumberMustNotBeNull() {
        StudentProfile studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(1L);
        studentProfile.setNameeng("Test Name");
        studentProfile.setNamethai("ทดสอบ ชื่อ");
        studentProfile.setIdcard("B58XXXXX");
        studentProfile.setIdnumber(null);
        studentProfile.setAddress("address");
        studentProfile.setAge(22);
        studentProfile.setBrithday(date);
        studentProfile.setBlood("AB");
        studentProfile.setTel("0834567891");
        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("idnumber", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testNameThaiMustNotBeNull() {
        StudentProfile studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(1L);
        studentProfile.setNameeng("Test Name");
        studentProfile.setNamethai(null);
        studentProfile.setIdcard("B58XXXXX");
        studentProfile.setIdnumber("1234567890123");
        studentProfile.setAddress("address");
        studentProfile.setAge(22);
        studentProfile.setBrithday(date);
        studentProfile.setBlood("AB");
        studentProfile.setTel("0834567891");
        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("namethai", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testNameEngMustNotBeNull() {
        StudentProfile studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(1L);
        studentProfile.setNameeng(null);
        studentProfile.setNamethai("ทดสอบ ชื่อ");
        studentProfile.setIdcard("B58XXXXX");
        studentProfile.setIdnumber("1234567890123");
        studentProfile.setAddress("address");
        studentProfile.setAge(22);
        studentProfile.setBrithday(date);
        studentProfile.setBlood("AB");
        studentProfile.setTel("0834567891");
        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("nameeng", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testIdCardMustNotBeNull() {
        StudentProfile studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(1L);
        studentProfile.setNameeng("Name Eng");
        studentProfile.setNamethai("ทดสอบ ชื่อ");
        studentProfile.setIdcard(null);
        studentProfile.setIdnumber("1234567890123");
        studentProfile.setAddress("address");
        studentProfile.setAge(22);
        studentProfile.setBrithday(date);
        studentProfile.setBlood("AB");
        studentProfile.setTel("0834567891");
        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("idcard", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testBirthDayMustNotBeNull() {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setStudentproId(1L);
        studentProfile.setNameeng("Name Eng");
        studentProfile.setNamethai("ทดสอบ ชื่อ");
        studentProfile.setIdcard("B58xxxxx");
        studentProfile.setIdnumber("1234567890123");
        studentProfile.setAddress("address");
        studentProfile.setAge(22);
        studentProfile.setBrithday(null);
        studentProfile.setBlood("AB");
        studentProfile.setTel("0834567891");
        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("brithday", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testAgeMustNotBeNull() {
        StudentProfile studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(1L);
        studentProfile.setNameeng("Name Eng");
        studentProfile.setNamethai("ทดสอบ ชื่อ");
        studentProfile.setIdcard("B58xxxxx");
        studentProfile.setIdnumber("1234567890123");
        studentProfile.setAddress("address");
        studentProfile.setAge(null);
        studentProfile.setBrithday(date);
        studentProfile.setBlood("AB");
        studentProfile.setTel("0834567891");
        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("age", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testBloodMustNotBeNull() {
        StudentProfile studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(1L);
        studentProfile.setNameeng("Name Eng");
        studentProfile.setNamethai("ทดสอบ ชื่อ");
        studentProfile.setIdcard("B58xxxxx");
        studentProfile.setIdnumber("1234567890123");
        studentProfile.setAddress("address");
        studentProfile.setAge(22);
        studentProfile.setBrithday(date);
        studentProfile.setBlood(null);
        studentProfile.setTel("0834567891");
        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("blood", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testTelMustNotBeNull() {
        StudentProfile studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(1L);
        studentProfile.setNameeng("Name Eng");
        studentProfile.setNamethai("ทดสอบ ชื่อ");
        studentProfile.setIdcard("B58xxxxx");
        studentProfile.setIdnumber("1234567890123");
        studentProfile.setAddress("address");
        studentProfile.setAge(22);
        studentProfile.setBrithday(date);
        studentProfile.setBlood("AB");
        studentProfile.setTel(null);

        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testAddressMustNotBeNull() {
        StudentProfile studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(1L);
        studentProfile.setNameeng("Name Eng");
        studentProfile.setNamethai("ทดสอบ ชื่อ");
        studentProfile.setIdcard("B58xxxxx");
        studentProfile.setIdnumber("1234567890123");
        studentProfile.setAddress(null);
        studentProfile.setAge(22);
        studentProfile.setBrithday(date);
        studentProfile.setBlood("AB");
        studentProfile.setTel("0834567891");

        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("address", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testIdNumberNotBe14Digit() {
        StudentProfile studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(1L);
        studentProfile.setNameeng("Name Eng");
        studentProfile.setNamethai("ทดสอบ ชื่อ");
        studentProfile.setIdcard("B58xxxxx");
        studentProfile.setIdnumber("12345678901234");
        studentProfile.setAddress("address");
        studentProfile.setAge(22);
        studentProfile.setBrithday(date);
        studentProfile.setBlood("AB");
        studentProfile.setTel("0834567891");

        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must match \"\\d{13}\"", v.getMessage());
        assertEquals("idnumber", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testIdNumberNotBe12Digit() {
        StudentProfile studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(1L);
        studentProfile.setNameeng("Name Eng");
        studentProfile.setNamethai("ทดสอบ ชื่อ");
        studentProfile.setIdcard("B58xxxxx");
        studentProfile.setIdnumber("123456789012");
        studentProfile.setAddress("address");
        studentProfile.setAge(22);
        studentProfile.setBrithday(date);
        studentProfile.setBlood("AB");
        studentProfile.setTel("0834567891");

        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must match \"\\d{13}\"", v.getMessage());
        assertEquals("idnumber", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testIdNumberNotBeCharactor() {
        StudentProfile studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(1L);
        studentProfile.setNameeng("Name Eng");
        studentProfile.setNamethai("ทดสอบ ชื่อ");
        studentProfile.setIdcard("B58xxxxx");
        studentProfile.setIdnumber("123456789012A");
        studentProfile.setAddress("address");
        studentProfile.setAge(22);
        studentProfile.setBrithday(date);
        studentProfile.setBlood("AB");
        studentProfile.setTel("0834567891");

        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must match \"\\d{13}\"", v.getMessage());
        assertEquals("idnumber", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testTelMustNotLessThan10() {
        StudentProfile studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(1L);
        studentProfile.setNameeng("Name Eng");
        studentProfile.setNamethai("ทดสอบ ชื่อ");
        studentProfile.setIdcard("B58xxxxx");
        studentProfile.setIdnumber("1234567890123");
        studentProfile.setAddress("address");
        studentProfile.setAge(22);
        studentProfile.setBrithday(date);
        studentProfile.setBlood("AB");
        studentProfile.setTel("083456789");

        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("size must be between 10 and 10", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testTelMustNotMoreThan10() {
        StudentProfile studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(1L);
        studentProfile.setNameeng("Name Eng");
        studentProfile.setNamethai("ทดสอบ ชื่อ");
        studentProfile.setIdcard("B58xxxxx");
        studentProfile.setIdnumber("1234567890123");
        studentProfile.setAddress("address");
        studentProfile.setAge(22);
        studentProfile.setBrithday(date);
        studentProfile.setBlood("AB");
        studentProfile.setTel("08345678901");

        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("size must be between 10 and 10", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }

}