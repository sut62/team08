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

import com.example.server.studentprofile.entity.*;
import com.example.server.studentprofile.repository.*;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class StudentProfileTest {

    private Validator validator;
    private Gender gender;
    private Major major;
    private Status status;

    @Autowired
    StudentProfileRepository StudentProfileRepository;

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    MajorRepository majorRepository;

    @Autowired
    StatusRepository statusRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        gender = new Gender();
        gender.setGender("ผู้หญิง");
        gender.setGenderId(6L);
        gender = genderRepository.saveAndFlush(gender);

        major = new Major();
        major.setMajor("วิศวกรรมศาสตร์");
        major.setMajorId(6L);
        major = majorRepository.saveAndFlush(major);

        status = new Status();
        status.setStatus("โสด");
        status.setStatusId(6L);
        status = statusRepository.saveAndFlush(status);
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
        studentProfile.setBlood("O");
        studentProfile.setTel("0834567891"); 
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);
        
        studentProfile = StudentProfileRepository.saveAndFlush(studentProfile);

        Optional<StudentProfile> StudentProfileCreated = StudentProfileRepository
                .findById(studentProfile.getStudentproId());
        assertEquals("Test Name", StudentProfileCreated.get().getNameeng());
        assertEquals("ทดสอบ ชื่อ", StudentProfileCreated.get().getNamethai());
        assertEquals("B58XXXXX", StudentProfileCreated.get().getIdcard());
        assertEquals("1234567890123", StudentProfileCreated.get().getIdnumber());
        assertEquals("address", StudentProfileCreated.get().getAddress());
        assertEquals(22, StudentProfileCreated.get().getAge());
        assertEquals(date, StudentProfileCreated.get().getBrithday());
        assertEquals("O", StudentProfileCreated.get().getBlood());
        assertEquals("0834567891", StudentProfileCreated.get().getTel());
        assertEquals(gender, StudentProfileCreated.get().getGender());
        assertEquals(major, StudentProfileCreated.get().getMajor());
        assertEquals(status, StudentProfileCreated.get().getStatus());
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
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);
        
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
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);

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
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);

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
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);

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
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);

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
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);

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
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);

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
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);

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
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);

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
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);

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
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);

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
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);

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
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);

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
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);

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
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);

        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("size must be between 10 and 10", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testMajorMustNotBeNull() {
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
        studentProfile.setTel("0834567890");
        studentProfile.setGender(gender);
        studentProfile.setMajor(null);
        studentProfile.setStatus(status);

        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("major", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testGenderMustNotBeNull() {
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
        studentProfile.setTel("0834567890");
        studentProfile.setGender(null);
        studentProfile.setMajor(major);
        studentProfile.setStatus(status);

        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("gender", v.getPropertyPath().toString());
    }

    @Test
    void B5807734_testStatusMustNotBeNull() {
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
        studentProfile.setTel("0834567890");
        studentProfile.setGender(gender);
        studentProfile.setMajor(major);
        studentProfile.setStatus(null);

        Set<ConstraintViolation<StudentProfile>> result = validator.validate(studentProfile);

        assertEquals(1, result.size());

        ConstraintViolation<StudentProfile> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("status", v.getPropertyPath().toString());
    }

}