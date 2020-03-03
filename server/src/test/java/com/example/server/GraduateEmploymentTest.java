// package com.example.server;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.dao.DataIntegrityViolationException;

// import javax.validation.ConstraintViolation;
// import javax.validation.Validation;
// import javax.validation.Validator;
// import javax.validation.ValidatorFactory;

// import com.example.server.Activities.entity.SchoolYear;
// import com.example.server.Activities.repository.SchoolYearRepository;
// import com.example.server.GraduateEmployment.entity.*;
// import com.example.server.GraduateEmployment.repository.*;
// import com.example.server.Graduates.entity.EducationLevel;
// import com.example.server.Graduates.repository.EducationLevelRepository;
// import com.example.server.studentprofile.entity.*;
// import com.example.server.studentprofile.repository.*;

// import java.util.Date;
// import java.util.Optional;
// import java.util.Set;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;

// @DataJpaTest
// public class GraduateEmploymentTest {

//     private Validator validator;
//     private StudentProfile studentProfile;
//     private Gender gender;
//     private Major major;
//     private Status status;
//     private SchoolYear schoolYear;
//     private EducationLevel educationLevel;
//     private WorkStatus workStatus;

//     @Autowired
//     private GraduateEmploymentRepository GraduateEmploymentRepository;

//     @Autowired
//     private WorkStatusRepository workStatusRepository;

//     @Autowired
//     private SchoolYearRepository schoolYearRepository;

//     @Autowired
//     private EducationLevelRepository educationLevelRepository;

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
//         ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//         validator = factory.getValidator();

//         workStatus = new WorkStatus();
//         workStatus.setStatus("ว่างงาน");
//         workStatus.setWorkstatusId(1L);
//         workStatus = workStatusRepository.saveAndFlush(workStatus);

//         educationLevel = new EducationLevel();
//         educationLevel.setEducaId(3L);
//         educationLevel.setEducalevel("ปริญญาตรี");
//         educationLevel = educationLevelRepository.saveAndFlush(educationLevel);

//         schoolYear = new SchoolYear();
//         schoolYear.setScyear(2562);
//         schoolYear.setYearid(2L);
//         schoolYear = schoolYearRepository.saveAndFlush(schoolYear);

//         gender = new Gender();
//         gender.setGender("ผู้หญิง");
//         gender.setGenderId(5L);
//         gender = genderRepository.saveAndFlush(gender);

//         major = new Major();
//         major.setMajor("วิศวกรรมศาสตร์");
//         major.setMajorId(5L);
//         major = majorRepository.saveAndFlush(major);

//         status = new Status();
//         status.setStatus("โสด");
//         status.setStatusId(5L);
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

//     @Test
//     void B5828104_testCreateGraduateEmploymentOK() {
//         GraduateEmployment graduateEmployment = new GraduateEmployment();
//         graduateEmployment.setGradempIdId(1L);
//         graduateEmployment.setWorkplace("บริษัทเสริมสุข");
//         graduateEmployment.setJopposition("วิศกร");
//         graduateEmployment.setEmail("Test_email@gmail.com");
//         graduateEmployment.setEducationLevel(educationLevel);
//         graduateEmployment.setStudentprofile(studentProfile);
//         graduateEmployment.setWorkStatus(workStatus);

//         graduateEmployment = GraduateEmploymentRepository.saveAndFlush(graduateEmployment);

//         Optional<GraduateEmployment> GraduateEmploymentCreated = GraduateEmploymentRepository.findById(graduateEmployment.getGradempId());
//         assertEquals(1L, GraduateEmploymentCreated.get().getGradempId());
//         assertEquals("บริษัทเสริมสุข", GraduateEmploymentCreated.get().getWorkplace());
//         assertEquals("วิศกร", GraduateEmploymentCreated.get().getJopposition());
//         assertEquals("Test_email@gmail.com", GraduateEmploymentCreated.get().getEmail());
//         assertEquals(educationLevel, GraduateEmploymentCreated.get().getEducationLevel());
//         assertEquals(studentProfile, GraduateEmploymentCreated.get().getStudentprofile());
//         assertEquals(workStatus, GraduateEmploymentCreated.get().getWorkStatus());
//     }

//     @Test 
//     void B5828104_testGraduateEmploymentIdMustNotBeNull() {
//         GraduateEmployment graduateEmployment = new GraduateEmployment();
//         graduateEmployment.setGradempIdId(null);
//         graduateEmployment.setWorkplace("บริษัทเสริมสุข");
//         graduateEmployment.setJopposition("วิศกร");
//         graduateEmployment.setEmail("Test_email@gmail.com");
//         graduateEmployment.setEducationLevel(educationLevel);
//         graduateEmployment.setStudentprofile(studentProfile);
//         graduateEmployment.setWorkStatus(workStatus);

//         Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

//         assertEquals(1, result.size());

//         ConstraintViolation<GraduateEmployment> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("gradempId", v.getPropertyPath().toString());
//     }

//     @Test 
//     void B5828104_testWorkplaceMustNotBeNull() {
//         GraduateEmployment graduateEmployment = new GraduateEmployment();
//         graduateEmployment.setGradempIdId(1L);
//         graduateEmployment.setWorkplace(null);
//         graduateEmployment.setJopposition("วิศกร");
//         graduateEmployment.setEmail("Test_email@gmail.com");
//         graduateEmployment.setEducationLevel(educationLevel);
//         graduateEmployment.setStudentprofile(studentProfile);
//         graduateEmployment.setWorkStatus(workStatus);

//         Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

//         assertEquals(1, result.size());

//         ConstraintViolation<GraduateEmployment> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("workplace", v.getPropertyPath().toString());
//     }

//     @Test 
//     void B5828104_testJobPositionMustNotBeNull() {
//         GraduateEmployment graduateEmployment = new GraduateEmployment();
//         graduateEmployment.setGradempIdId(1L);
//         graduateEmployment.setWorkplace("บริษัทเสริมสุข");
//         graduateEmployment.setJopposition(null);
//         graduateEmployment.setEmail("Test_email@gmail.com");
//         graduateEmployment.setEducationLevel(educationLevel);
//         graduateEmployment.setStudentprofile(studentProfile);
//         graduateEmployment.setWorkStatus(workStatus);

//         Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

//         assertEquals(1, result.size());

//         ConstraintViolation<GraduateEmployment> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("jopposition", v.getPropertyPath().toString());
//     }

//     @Test 
//     void B5828104_testEmailMustNotBeNull() {
//         GraduateEmployment graduateEmployment = new GraduateEmployment();
//         graduateEmployment.setGradempIdId(1L);
//         graduateEmployment.setWorkplace("บริษัทเสริมสุข");
//         graduateEmployment.setJopposition("วิศกร");
//         graduateEmployment.setEmail(null);
//         graduateEmployment.setEducationLevel(educationLevel);
//         graduateEmployment.setStudentprofile(studentProfile);
//         graduateEmployment.setWorkStatus(workStatus);

//         Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

//         assertEquals(1, result.size());

//         ConstraintViolation<GraduateEmployment> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("email", v.getPropertyPath().toString());
//     }

//     @Test 
//     void B5828104_testEmailMustCorrectPattern() {
//         GraduateEmployment graduateEmployment = new GraduateEmployment();
//         graduateEmployment.setGradempIdId(1L);
//         graduateEmployment.setWorkplace("บริษัทเสริมสุข");
//         graduateEmployment.setJopposition("วิศกร");
//         graduateEmployment.setEmail("Test_#$emial@.com");
//         graduateEmployment.setEducationLevel(educationLevel);
//         graduateEmployment.setStudentprofile(studentProfile);
//         graduateEmployment.setWorkStatus(workStatus);

//         Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

//         assertEquals(1, result.size());

//         ConstraintViolation<GraduateEmployment> v = result.iterator().next();
//         assertEquals("Email pattern should be valid", v.getMessage());
//         assertEquals("email", v.getPropertyPath().toString());
//     }

//     @Test 
//     void B5828104_testWorkplaceMustNotLessThan5() {
//         GraduateEmployment graduateEmployment = new GraduateEmployment();
//         graduateEmployment.setGradempIdId(1L);
//         graduateEmployment.setWorkplace("สถาน");
//         graduateEmployment.setJopposition("วิศกร");
//         graduateEmployment.setEmail("Test_email@gmail.com");
//         graduateEmployment.setEducationLevel(educationLevel);
//         graduateEmployment.setStudentprofile(studentProfile);
//         graduateEmployment.setWorkStatus(workStatus);

//         Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

//         assertEquals(1, result.size());

//         ConstraintViolation<GraduateEmployment> v = result.iterator().next();
//         assertEquals("size must be between 5 and 30", v.getMessage());
//         assertEquals("workplace", v.getPropertyPath().toString());
//     }

//     @Test 
//     void B5828104_testWorkplaceMustNotMoreThan30() {
//         GraduateEmployment graduateEmployment = new GraduateEmployment();
//         graduateEmployment.setGradempIdId(1L);
//         graduateEmployment.setWorkplace("สถานที่ทำงานสำหรับนักศึกษาจบใหม่");
//         graduateEmployment.setJopposition("วิศกร");
//         graduateEmployment.setEmail("Test_email@gmail.com");
//         graduateEmployment.setEducationLevel(educationLevel);
//         graduateEmployment.setStudentprofile(studentProfile);
//         graduateEmployment.setWorkStatus(workStatus);

//         Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

//         assertEquals(1, result.size());

//         ConstraintViolation<GraduateEmployment> v = result.iterator().next();
//         assertEquals("size must be between 5 and 30", v.getMessage());
//         assertEquals("workplace", v.getPropertyPath().toString());
//     }

//     @Test 
//     void B5828104_testEducationLevelMustNotBeNull() {
//         GraduateEmployment graduateEmployment = new GraduateEmployment();
//         graduateEmployment.setGradempIdId(1L);
//         graduateEmployment.setWorkplace("สถานที่ทำงาน");
//         graduateEmployment.setJopposition("วิศกร");
//         graduateEmployment.setEmail("Test_email@gmail.com");
//         graduateEmployment.setEducationLevel(null);
//         graduateEmployment.setStudentprofile(studentProfile);
//         graduateEmployment.setWorkStatus(workStatus);

//         Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

//         assertEquals(1, result.size());

//         ConstraintViolation<GraduateEmployment> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("educationlevel", v.getPropertyPath().toString());
//     }

//     @Test 
//     void B5828104_testStudentProfileMustNotBeNull() {
//         GraduateEmployment graduateEmployment = new GraduateEmployment();
//         graduateEmployment.setGradempIdId(1L);
//         graduateEmployment.setWorkplace("สถานที่ทำงาน");
//         graduateEmployment.setJopposition("วิศกร");
//         graduateEmployment.setEmail("Test_email@gmail.com");
//         graduateEmployment.setEducationLevel(educationLevel);
//         graduateEmployment.setStudentprofile(null);
//         graduateEmployment.setWorkStatus(workStatus);

//         Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

//         assertEquals(1, result.size());

//         ConstraintViolation<GraduateEmployment> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("studentprofile", v.getPropertyPath().toString());
//     }

//     @Test 
//     void B5828104_testWorkStatusMustNotBeNull() {
//         GraduateEmployment graduateEmployment = new GraduateEmployment();
//         graduateEmployment.setGradempIdId(1L);
//         graduateEmployment.setWorkplace("สถานที่ทำงาน");
//         graduateEmployment.setJopposition("วิศกร");
//         graduateEmployment.setEmail("Test_email@gmail.com");
//         graduateEmployment.setEducationLevel(educationLevel);
//         graduateEmployment.setStudentprofile(studentProfile);
//         graduateEmployment.setWorkStatus(null);

//         Set<ConstraintViolation<GraduateEmployment>> result = validator.validate(graduateEmployment);

//         assertEquals(1, result.size());

//         ConstraintViolation<GraduateEmployment> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("workstatus", v.getPropertyPath().toString());
//     }
// }