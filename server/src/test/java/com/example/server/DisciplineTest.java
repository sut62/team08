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

import com.example.server.Discipline.entity.*;
import com.example.server.Discipline.repository.*;
import com.example.server.studentprofile.entity.*;
import com.example.server.studentprofile.repository.*;

import java.util.Optional;
import java.util.Set;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class DisciplineTest {

    private Validator validator;
    private Gender gender;
    private Major major;
    private Status status;
    private StudentProfile studentProfile;
    private Breakrule breakrule;
    private Punish punish;
    
    @Autowired
    private StudentProfileRepository studentProfileRepository;
    
	@Autowired
	private DisciplineRepository disciplineRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private StatusRepository statusRepository;
    
    @Autowired
    private BreakruleRepository breakruleRepository;
    
    @Autowired
    private PunishRepository punishRepository;
    
	@BeforeEach
	public void setup() {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        breakrule = new Breakrule();
        breakrule.setRuleId(1L);
        breakrule.setRuleType("เล่นการพนัน");
        breakrule = breakruleRepository.saveAndFlush(breakrule);

        punish = new Punish();
        punish.setPunishId(1L);
        punish.setPunishType("ตักเตือนเป็นลายลักษณ์อักษร");
        punish = punishRepository.saveAndFlush(punish);

        gender = new Gender();
        gender.setGender("ผู้หญิง");
        gender.setGenderId(2L);
        gender = genderRepository.saveAndFlush(gender);

        major = new Major();
        major.setMajor("วิศวกรรมศาสตร์");
        major.setMajorId(2L);
        major = majorRepository.saveAndFlush(major);

        status = new Status();
        status.setStatus("โสด");
        status.setStatusId(2L);
        status = statusRepository.saveAndFlush(status);

        studentProfile = new StudentProfile();
        Date date = new Date();
        studentProfile.setStudentproId(99L);
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
    void B5801206_testCreatedDisciplineOK() {
        Discipline discipline = new Discipline();
        Date date = new Date();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(50L);
        discipline.setSince(date);
        discipline.setUntil(date);
        discipline.setBreakrule(breakrule);
        discipline.setPunish(punish);
        discipline.setStudentprofile(studentProfile);
        

        discipline = disciplineRepository.saveAndFlush(discipline);

        Optional<Discipline> DisciplineCreated = disciplineRepository
                .findById(discipline.getDisciplineId());
        assertEquals(1L, DisciplineCreated.get().getDisciplineId());
        assertEquals("2562", DisciplineCreated.get().getSchoolyear());
        assertEquals(50L, DisciplineCreated.get().getPoint());
        assertEquals(date, DisciplineCreated.get().getSince());
        assertEquals(date, DisciplineCreated.get().getUntil());
        assertEquals(breakrule, DisciplineCreated.get().getBreakrule());
        assertEquals(punish, DisciplineCreated.get().getPunish());
        assertEquals(studentProfile, DisciplineCreated.get().getStudentprofile());
    }

    // Schoolyear กรณีที่ 2 น้อยกว่า 4 ตัว
    @Test
    void B5801206_testSchoolyearSizeLessThan4() {
        Discipline discipline = new Discipline();
        Date date = new Date();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("123");
        discipline.setPoint(50L);
        discipline.setSince(date);
        discipline.setUntil(date);
        discipline.setBreakrule(breakrule);
        discipline.setPunish(punish);
        discipline.setStudentprofile(studentProfile);

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
        Date date = new Date();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("12345");
        discipline.setPoint(50L);
        discipline.setSince(date);
        discipline.setUntil(date);
        discipline.setBreakrule(breakrule);
        discipline.setPunish(punish);
        discipline.setStudentprofile(studentProfile);

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
        Date date = new Date();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("123A");
        discipline.setPoint(50L);
        discipline.setSince(date);
        discipline.setUntil(date);
        discipline.setBreakrule(breakrule);
        discipline.setPunish(punish);
        discipline.setStudentprofile(studentProfile);

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
        Date date = new Date();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear(null);
        discipline.setPoint(50L);
        discipline.setSince(date);
        discipline.setUntil(date);
        discipline.setBreakrule(breakrule);
        discipline.setPunish(punish);
        discipline.setStudentprofile(studentProfile);

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("schoolyear", v.getPropertyPath().toString());
    }

    // Point กรณีที่ 6 น้อยกว่า 10
    @Test
    void B5801206_testPointMustNotLessThan10() {
        Discipline discipline = new Discipline();
        Date date = new Date();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(9L);
        discipline.setSince(date);
        discipline.setUntil(date);
        discipline.setBreakrule(breakrule);
        discipline.setPunish(punish);
        discipline.setStudentprofile(studentProfile);

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        assertEquals(1, result.size());

        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must be greater than or equal to 10", v.getMessage());
        assertEquals("point", v.getPropertyPath().toString());
    }

    // Point กรณีที่ 7 มากกว่า 100
    @Test
    void B5801206_testPointMustNotMoreThan100() {
        Discipline discipline = new Discipline();
        Date date = new Date();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(101L);
        discipline.setSince(date);
        discipline.setUntil(date);
        discipline.setBreakrule(breakrule);
        discipline.setPunish(punish);
        discipline.setStudentprofile(studentProfile);

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        assertEquals(1, result.size());

        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must be less than or equal to 100", v.getMessage());
        assertEquals("point", v.getPropertyPath().toString());
    }

    // Point กรณีที่ 8 ต้องไม่เป็นค่าว่าง
    @Test
    void B5801206_testPointMustNotBeNull() {
        Discipline discipline = new Discipline();
        Date date = new Date();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(null);
        discipline.setSince(date);
        discipline.setUntil(date);
        discipline.setBreakrule(breakrule);
        discipline.setPunish(punish);
        discipline.setStudentprofile(studentProfile);

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("point", v.getPropertyPath().toString());
    }

    // Since กรณีที่ 9 ต้องไม่เป็นค่าว่าง
    @Test
    void B5801206_testSinceMustNotBeNull() {
        Discipline discipline = new Discipline();
        Date date = new Date();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(50L);
        discipline.setSince(null);
        discipline.setUntil(date);
        discipline.setBreakrule(breakrule);
        discipline.setPunish(punish);
        discipline.setStudentprofile(studentProfile);

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        assertEquals(1, result.size());

        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("since", v.getPropertyPath().toString());
    }

    // Until กรณีที่ 10 ต้องไม่เป็นค่าว่าง
    @Test
    void B5801206_testUntilMustNotBeNull() {
        Discipline discipline = new Discipline();
        Date date = new Date();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(50L);
        discipline.setSince(date);
        discipline.setUntil(null);
        discipline.setBreakrule(breakrule);
        discipline.setPunish(punish);
        discipline.setStudentprofile(studentProfile);

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        assertEquals(1, result.size());

        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("until", v.getPropertyPath().toString());
    }

    @Test
    void B5801206_testBreakruleMustNotBeNull() {
        Discipline discipline = new Discipline();
        Date date = new Date();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(50L);
        discipline.setSince(date);
        discipline.setUntil(date);
        discipline.setBreakrule(null);
        discipline.setPunish(punish);
        discipline.setStudentprofile(studentProfile);

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        assertEquals(1, result.size());

        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("breakrule", v.getPropertyPath().toString());
    }

    @Test
    void B5801206_testPunishMustNotBeNull() {
        Discipline discipline = new Discipline();
        Date date = new Date();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(50L);
        discipline.setSince(date);
        discipline.setUntil(date);
        discipline.setBreakrule(breakrule);
        discipline.setPunish(null);
        discipline.setStudentprofile(studentProfile);

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        assertEquals(1, result.size());

        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("punish", v.getPropertyPath().toString());
    }

    @Test
    void B5801206_testStudentProfileMustNotBeNull() {
        Discipline discipline = new Discipline();
        Date date = new Date();
        discipline.setDisciplineId(1L);
        discipline.setSchoolyear("2562");
        discipline.setPoint(50L);
        discipline.setSince(date);
        discipline.setUntil(date);
        discipline.setBreakrule(breakrule);
        discipline.setPunish(punish);
        discipline.setStudentprofile(null);

        Set<ConstraintViolation<Discipline>> result = validator.validate(discipline);

        assertEquals(1, result.size());

        ConstraintViolation<Discipline> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("studentprofile", v.getPropertyPath().toString());
    }

}
