// package com.example.server;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.dao.DataIntegrityViolationException;

// import javax.validation.ConstraintViolation;
// import javax.validation.ConstraintViolationException;
// import javax.validation.Validation;
// import javax.validation.Validator;
// import javax.validation.ValidatorFactory;

// import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;

// import com.example.server.Scholarship.entity.*;
// import com.example.server.Scholarship.repository.*;
// import com.example.server.studentprofile.entity.*;
// import com.example.server.studentprofile.repository.*;

// import java.util.Optional;
// import java.util.Set;
// import java.util.Date;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;

// @DataJpaTest
// public class ScholarshipTest {

//     private Validator validator;
//     private StudentProfile studentProfile;
//     private Gender gender;
//     private Major major;
//     private Status status;
//     private ScholarshipType scholarshipType;
//     private ScholarshipOfficer  scholarshipOfficer;

//     @Autowired
//     private ScholarshipRepository scholarshipRepository;

//     @Autowired
//     private ScholarshipOfficerRepository scholarshipOfficerRepository;

//     @Autowired
//     private ScholarshipTypeRepository scholarshipTypeRepository;

//     @Autowired
//     private StudentProfileRepository studentProfileRepository;
    
//     @Autowired
//     private GenderRepository genderRepository;

//     @Autowired
//     private MajorRepository majorRepository;

//     @Autowired
//     private StatusRepository statusRepository;

//     @BeforeEach
//     public void setup() {
//         final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//         validator = factory.getValidator();

//         scholarshipOfficer = new ScholarshipOfficer();
//         scholarshipOfficer.setOfficerid(1L);
//         scholarshipOfficer.setOfficername("officername");
//         scholarshipOfficer = scholarshipOfficerRepository.saveAndFlush(scholarshipOfficer);

//         scholarshipType = new ScholarshipType();
//         scholarshipType.setScholarshipTypeid(1L);
//         scholarshipType.setGoodEducation("ทุนศักยบันณฑิต");
//         scholarshipType = scholarshipTypeRepository.saveAndFlush(scholarshipType);

//         gender = new Gender();
//         gender.setGender("ผู้หญิง");
//         gender.setGenderId(7L);
//         gender = genderRepository.saveAndFlush(gender);

//         major = new Major();
//         major.setMajor("วิศวกรรมศาสตร์");
//         major.setMajorId(7L);
//         major = majorRepository.saveAndFlush(major);

//         status = new Status();
//         status.setStatus("โสด");
//         status.setStatusId(7L);
//         status = statusRepository.saveAndFlush(status);

//         studentProfile = new StudentProfile();
//         Date date = new Date();
//         studentProfile.setStudentproId(95L);
//         studentProfile.setNameeng("Test Name");
//         studentProfile.setNamethai("ทดสอบ ชื่อ");
//         studentProfile.setIdcard("B58XXXXX");
//         studentProfile.setIdnumber("1234567890123");
//         studentProfile.setAddress("address");
//         studentProfile.setAge(22);
//         studentProfile.setBrithday(date);
//         studentProfile.setBlood("AB");
//         studentProfile.setTel("0834567891");
//         studentProfile.setGender(gender);
//         studentProfile.setStatus(status);
//         studentProfile.setMajor(major);   
//         studentProfile = studentProfileRepository.saveAndFlush(studentProfile);
//     }

//     // กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
//     @Test
//     void B5814619_testCreatedScholarshipOK() {
//         Scholarship scholarship = new Scholarship();
//         final Date paydate = new Date();
//         scholarship.setScholarshipid(1L);
//         scholarship.setPaydate(paydate);
//         scholarship.setMoney("12345");
//         scholarship.setScholarshipType(scholarshipType);
//         scholarship.setScholarshipOfficer(scholarshipOfficer);
//         scholarship.setStudentProfile(studentProfile);

//         scholarship = scholarshipRepository.saveAndFlush(scholarship);

//         Optional<Scholarship> ScholarshipCreated = scholarshipRepository
//                 .findById(scholarship.getScholarshipid());
//         assertEquals(1L, ScholarshipCreated.get().getScholarshipid());
//         assertEquals(paydate, ScholarshipCreated.get().getPaydate());
//         assertEquals("12345", ScholarshipCreated.get().getMoney());
//         assertEquals(scholarshipOfficer, ScholarshipCreated.get().getScholarshipOfficer());
//         assertEquals(scholarshipType, ScholarshipCreated.get().getScholarshipType());
//         assertEquals(studentProfile, ScholarshipCreated.get().getStudentProfile());
//     }

//     // Money กรณีที่ 2 น้อยกว่า 3 ตัว
//     @Test
//     void B5814619_testMoneySizeLessThan3() {
//         final Scholarship scholarship = new Scholarship();
//         final Date paydate = new Date();
//         scholarship.setScholarshipid(1L);
//         scholarship.setPaydate(paydate);
//         scholarship.setMoney("01");
//         scholarship.setScholarshipType(scholarshipType);
//         scholarship.setScholarshipOfficer(scholarshipOfficer);
//         scholarship.setStudentProfile(studentProfile);

//         final Set<ConstraintViolation<Scholarship>> result = validator.validate(scholarship);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         final ConstraintViolation<Scholarship> v = result.iterator().next();
//         assertEquals("size must be between 3 and 5", v.getMessage());
//         assertEquals("money", v.getPropertyPath().toString());
//     }

//     // Money กรณีที่ 3 มากกว่า 5 ตัว
//     @Test
//     void B5814619_testMoneySizeMorethan5() {
//         final Scholarship scholarship = new Scholarship();
//         final Date paydate = new Date();
//         scholarship.setScholarshipid(1L);
//         scholarship.setPaydate(paydate);
//         scholarship.setMoney("1234567");
//         scholarship.setScholarshipType(scholarshipType);
//         scholarship.setScholarshipOfficer(scholarshipOfficer);
//         scholarship.setStudentProfile(studentProfile);

//         final Set<ConstraintViolation<Scholarship>> result = validator.validate(scholarship);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         final ConstraintViolation<Scholarship> v = result.iterator().next();
//         assertEquals("size must be between 3 and 5", v.getMessage());
//         assertEquals("money", v.getPropertyPath().toString());
//     }

//     // Money กรณีที่ 4 จำนวนเงินต้องไม่เป็นค่าว่าง
//     @Test
//     void B5814619_testMoneyMustNotBeNull() {
//         final Scholarship scholarship = new Scholarship();
//         final Date paydate = new Date();
//         scholarship.setScholarshipid(1L);
//         scholarship.setPaydate(paydate);
//         scholarship.setMoney(null);
//         scholarship.setScholarshipType(scholarshipType);
//         scholarship.setScholarshipOfficer(scholarshipOfficer);
//         scholarship.setStudentProfile(studentProfile);

//         final Set<ConstraintViolation<Scholarship>> result = validator.validate(scholarship);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         final ConstraintViolation<Scholarship> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("money", v.getPropertyPath().toString());
//     }

//     // Date กรณีที่ 1 ต้องไม่เป็นค่าว่าง
//     /*@Test
//     void B5814619_testDateMustNotBeNull() {
//         final Scholarship scholarship = new Scholarship();
//         scholarship.setScholarshipid(1L);
//         scholarship.setPaydate(null);
//         scholarship.setMoney("123456789");

//         final Set<ConstraintViolation<Scholarship>> result = validator.validate(scholarship);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         final ConstraintViolation<Scholarship> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("paydate", v.getPropertyPath().toString());
//     }*/

//     @Test
//     void B5814619_testDateMustNotBeNull() {
//         Scholarship scholarship = new Scholarship();
//         scholarship.setScholarshipid(1L);
//         scholarship.setPaydate(null);
//         scholarship.setMoney("12345");
//         scholarship.setScholarshipType(scholarshipType);
//         scholarship.setScholarshipOfficer(scholarshipOfficer);
//         scholarship.setStudentProfile(studentProfile);

//         Set<ConstraintViolation<Scholarship>> result = validator.validate(scholarship);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<Scholarship> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("paydate", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5814619_testScholarshipTypeMustNotBeNull() {
//         Scholarship scholarship = new Scholarship();
//         scholarship.setScholarshipid(1L);
//         scholarship.setPaydate(new Date());
//         scholarship.setMoney("12345");
//         scholarship.setScholarshipType(null);
//         scholarship.setScholarshipOfficer(scholarshipOfficer);
//         scholarship.setStudentProfile(studentProfile);

//         Set<ConstraintViolation<Scholarship>> result = validator.validate(scholarship);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<Scholarship> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("scholarshipType", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5814619_testScholarshipOfficerMustNotBeNull() {
//         Scholarship scholarship = new Scholarship();
//         scholarship.setScholarshipid(1L);
//         scholarship.setPaydate(new Date());
//         scholarship.setMoney("12345");
//         scholarship.setScholarshipType(scholarshipType);
//         scholarship.setScholarshipOfficer(null);
//         scholarship.setStudentProfile(studentProfile);

//         Set<ConstraintViolation<Scholarship>> result = validator.validate(scholarship);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<Scholarship> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("scholarshipOfficer", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5814619_testStudentProfileMustNotBeNull() {
//         Scholarship scholarship = new Scholarship();
//         scholarship.setScholarshipid(1L);
//         scholarship.setPaydate(new Date());
//         scholarship.setMoney("12345");
//         scholarship.setScholarshipType(scholarshipType);
//         scholarship.setScholarshipOfficer(scholarshipOfficer);
//         scholarship.setStudentProfile(null);

//         Set<ConstraintViolation<Scholarship>> result = validator.validate(scholarship);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<Scholarship> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("studentProfile", v.getPropertyPath().toString());
//     }

// }
