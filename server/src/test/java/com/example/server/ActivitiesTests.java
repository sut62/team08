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

import com.example.server.Activities.entity.*;
import com.example.server.Activities.repository.*;
import com.example.server.studentprofile.entity.*;
import com.example.server.studentprofile.repository.*;

import java.util.Optional;
import java.util.Set;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class ActivitiesTests {

    private Validator validator;
    private SchoolYear schoolYear;
    private Institution institution;
    private StudentProfile studentProfile;
    private Gender gender;
    private Major major;
    private Status status;

	@Autowired
	private ActivitiesRepository activitiesRepository;

    @Autowired
    private SchoolYearRepository schoolYearRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;
    
    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private StatusRepository statusRepository;


	@BeforeEach
	public void setup() {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        schoolYear = new SchoolYear();
        schoolYear.setScyear(2562);
        schoolYear.setYearid(1L);
        schoolYear = schoolYearRepository.saveAndFlush(schoolYear);

        institution = new Institution();
        institution.setInsname("ชมรมคอมพิวเตอร์");
        institution.setInsid(1L);
        institution = institutionRepository.saveAndFlush(institution);

        gender = new Gender();
        gender.setGender("ผู้หญิง");
        gender.setGenderId(1L);
        gender = genderRepository.saveAndFlush(gender);

        major = new Major();
        major.setMajor("วิศวกรรมศาสตร์");
        major.setMajorId(1L);
        major = majorRepository.saveAndFlush(major);

        status = new Status();
        status.setStatus("โสด");
        status.setStatusId(1L);
        status = statusRepository.saveAndFlush(status);

        studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(98L);
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
        studentProfile.setStatus(status);
        studentProfile.setMajor(major);   
        studentProfile = studentProfileRepository.saveAndFlush(studentProfile);
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
        activities.setInstitution(institution);
        activities.setSchoolyear(schoolYear);
        activities.setStudentprofile(studentProfile);

        activities = activitiesRepository.saveAndFlush(activities);

        Optional<Activities> ActivitiesCreated = activitiesRepository
                .findById(activities.getActid());
        assertEquals(1L, ActivitiesCreated.get().getActid());
        assertEquals(date, ActivitiesCreated.get().getDate());
        assertEquals("กกกกก", ActivitiesCreated.get().getActname());
        assertEquals("0812345678", ActivitiesCreated.get().getTel());
        assertEquals(institution, ActivitiesCreated.get().getInstitution());
        assertEquals(schoolYear, ActivitiesCreated.get().getSchoolyear());
        assertEquals(studentProfile, ActivitiesCreated.get().getStudentprofile());
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
        activities.setInstitution(institution);
        activities.setSchoolyear(schoolYear);
        activities.setStudentprofile(studentProfile);

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
        activities.setInstitution(institution);
        activities.setSchoolyear(schoolYear);
        activities.setStudentprofile(studentProfile);

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
        activities.setInstitution(institution);
        activities.setSchoolyear(schoolYear);
        activities.setStudentprofile(studentProfile);

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
        activities.setInstitution(institution);
        activities.setSchoolyear(schoolYear);
        activities.setStudentprofile(studentProfile);

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
        activities.setInstitution(institution);
        activities.setSchoolyear(schoolYear);
        activities.setStudentprofile(studentProfile);

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
        activities.setInstitution(institution);
        activities.setSchoolyear(schoolYear);
        activities.setStudentprofile(studentProfile);

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
        activities.setInstitution(institution);
        activities.setSchoolyear(schoolYear);
        activities.setStudentprofile(studentProfile);

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
        activities.setInstitution(institution);
        activities.setSchoolyear(schoolYear);
        activities.setStudentprofile(studentProfile);

        final Set<ConstraintViolation<Activities>> result = validator.validate(activities);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Activities> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());
    }
    // institution not be null
    @Test
    void B5815173_testInstitutionMustNotBeNull(){
        Activities activities = new Activities();
        Date date = new Date();
        activities.setActid(1L);
        activities.setActname("กกกกก");
        activities.setTel("0812345678");
        activities.setDate(date);
        activities.setInstitution(null);
        activities.setSchoolyear(schoolYear);
        activities.setStudentprofile(studentProfile);

        Set<ConstraintViolation<Activities>> result = validator.validate(activities);

        assertEquals(1, result.size());

        ConstraintViolation<Activities> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("institution", v.getPropertyPath().toString());     
    }
    // schoolyear not be null
    @Test
    void B5815173_testSchoolYearMustNotBeNull(){
        Activities activities = new Activities();
        Date date = new Date();
        activities.setActid(1L);
        activities.setActname("กกกกก");
        activities.setTel("0812345678");
        activities.setDate(date);
        activities.setInstitution(institution);
        activities.setSchoolyear(null);
        activities.setStudentprofile(studentProfile);

        Set<ConstraintViolation<Activities>> result = validator.validate(activities);

        assertEquals(1, result.size());

        ConstraintViolation<Activities> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("schoolyear", v.getPropertyPath().toString());     
    }
    // studentProfile not be null
    @Test
    void B5815173_testStudentProfileMustNotBeNull(){
        Activities activities = new Activities();
        Date date = new Date();
        activities.setActid(1L);
        activities.setActname("กกกกก");
        activities.setTel("0812345678");
        activities.setDate(date);
        activities.setInstitution(institution);
        activities.setSchoolyear(schoolYear);
        activities.setStudentprofile(null);

        Set<ConstraintViolation<Activities>> result = validator.validate(activities);

        assertEquals(1, result.size());

        ConstraintViolation<Activities> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("studentprofile", v.getPropertyPath().toString());     
    }

}
