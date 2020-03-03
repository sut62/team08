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
// import com.example.server.Graduates.entity.*;
// import com.example.server.Graduates.repository.*;
// import com.example.server.studentprofile.entity.*;
// import com.example.server.studentprofile.repository.*;

// import java.util.Date;
// import java.util.Optional;
// import java.util.Set;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;

// @DataJpaTest
// public class GraduatesTest {

//     private Validator validator;
//     private StudentProfile studentProfile;
//     private Gender gender;
//     private Major major;
//     private Status status;
//     private SchoolYear schoolYear;
//     private EducationLevel educationLevel;

//     @Autowired
//     private GraduatesRepository GraduatesRepository;

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

//         educationLevel = new EducationLevel();
//         educationLevel.setEducaId(10L);
//         educationLevel.setEducalevel("ปริญญาตรี");
//         educationLevel = educationLevelRepository.saveAndFlush(educationLevel);

//         schoolYear = new SchoolYear();
//         schoolYear.setScyear(2562);
//         schoolYear.setYearid(7L);
//         schoolYear = schoolYearRepository.saveAndFlush(schoolYear);

//         gender = new Gender();
//         gender.setGender("ผู้หญิง");
//         gender.setGenderId(3L);
//         gender = genderRepository.saveAndFlush(gender);

//         major = new Major();
//         major.setMajor("วิศวกรรมศาสตร์");
//         major.setMajorId(3L);
//         major = majorRepository.saveAndFlush(major);

//         status = new Status();
//         status.setStatus("โสด");
//         status.setStatusId(3L);
//         status = statusRepository.saveAndFlush(status);

//         studentProfile = new StudentProfile();
//         Date date = new Date();
//         studentProfile.setStudentproId(97L);
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
//     void B5807734_testCreateGraduatesOK() {
//         Graduates graduates = new Graduates();
//         Date date = new Date();
// 		graduates.setGraduaId(1L);
//         graduates.setHonor("เกียรตินิยม");
//         graduates.setGraduadate(date);
//         graduates.setGraduation("เข้ารับ");
//         graduates.setEducationlevel(educationLevel);
//         graduates.setSchoolyear(schoolYear);
//         graduates.setStudentprofile(studentProfile);

// 		graduates = GraduatesRepository.saveAndFlush(graduates);

// 		Optional<Graduates> GraduatesCreated = GraduatesRepository.findById(graduates.getGraduaId());
//         assertEquals(1L, GraduatesCreated.get().getGraduaId());
//         assertEquals("เกียรตินิยม", GraduatesCreated.get().getHonor());
//         assertEquals(date, GraduatesCreated.get().getGraduadate());
//         assertEquals("เข้ารับ", GraduatesCreated.get().getGraduation());
//         assertEquals(educationLevel, GraduatesCreated.get().getEducationlevel());
//         assertEquals(schoolYear, GraduatesCreated.get().getSchoolyear());
//         assertEquals(studentProfile, GraduatesCreated.get().getStudentprofile());
//     }

// 	@Test
//     void B5807734_testGraduatesIdMustNotBeNull() {
//         Graduates graduates = new Graduates();
//         Date date = new Date();
// 		graduates.setGraduaId(null);
//         graduates.setHonor("เกียรตินิยม");
//         graduates.setGraduadate(date);
//         graduates.setGraduation("เข้ารับ");
//         graduates.setEducationlevel(educationLevel);
//         graduates.setSchoolyear(schoolYear);
//         graduates.setStudentprofile(studentProfile);

// 		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
// 		assertEquals(1, result.size());
		
//         ConstraintViolation<Graduates> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("graduaId", v.getPropertyPath().toString());
//     }
	
// 	@Test
//     void B5807734_testHonorMustNotBeNull() {
//         Graduates graduates = new Graduates();
//         Date date = new Date();
// 		graduates.setGraduaId(1L);
//         graduates.setHonor(null);
//         graduates.setGraduadate(date);
//         graduates.setGraduation("เข้ารับ");
//         graduates.setEducationlevel(educationLevel);
//         graduates.setSchoolyear(schoolYear);
//         graduates.setStudentprofile(studentProfile);
        
// 		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
// 		assertEquals(1, result.size());
		
//         ConstraintViolation<Graduates> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("honor", v.getPropertyPath().toString());
//     }
//     @Test
//     void B5807734_testGraduateDateMustNotBeNull() {
//         Graduates graduates = new Graduates();
// 		graduates.setGraduaId(1L);
//         graduates.setHonor("เกียรตินิยม");
//         graduates.setGraduadate(null);
//         graduates.setGraduation("เข้ารับ");
//         graduates.setEducationlevel(educationLevel);
//         graduates.setSchoolyear(schoolYear);
//         graduates.setStudentprofile(studentProfile);

// 		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
// 		assertEquals(1, result.size());
		
//         ConstraintViolation<Graduates> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("graduadate", v.getPropertyPath().toString());
//     }
//     @Test
//     void B5807734_testGraduationMustNotBeNull() {
//         Graduates graduates = new Graduates();
//         Date date = new Date();
// 		graduates.setGraduaId(1L);
//         graduates.setHonor("เกียรตินิยม");
//         graduates.setGraduadate(date);
//         graduates.setGraduation(null);
//         graduates.setEducationlevel(educationLevel);
//         graduates.setSchoolyear(schoolYear);
//         graduates.setStudentprofile(studentProfile);

// 		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
// 		assertEquals(1, result.size());
		
//         ConstraintViolation<Graduates> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("graduation", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5807734_testHonorMustNotLessThan5() {
//         Graduates graduates = new Graduates();
//         Date date = new Date();
// 		graduates.setGraduaId(1L);
//         graduates.setHonor("เกีย");
//         graduates.setGraduadate(date);
//         graduates.setGraduation("เข้ารับ");
//         graduates.setEducationlevel(educationLevel);
//         graduates.setSchoolyear(schoolYear);
//         graduates.setStudentprofile(studentProfile);

// 		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
// 		assertEquals(1, result.size());
		
//         ConstraintViolation<Graduates> v = result.iterator().next();
//         assertEquals("size must be between 5 and 20", v.getMessage());
//         assertEquals("honor", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5807734_testHonorMustNotMoreThan20() {
//         Graduates graduates = new Graduates();
//         Date date = new Date();
// 		graduates.setGraduaId(1L);
//         graduates.setHonor("ได้รับ เกียรตินิยมอันดับ ที่ 2");
//         graduates.setGraduadate(date);
//         graduates.setGraduation("เข้ารับ");
//         graduates.setEducationlevel(educationLevel);
//         graduates.setSchoolyear(schoolYear);
//         graduates.setStudentprofile(studentProfile);

// 		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
// 		assertEquals(1, result.size());
		
//         ConstraintViolation<Graduates> v = result.iterator().next();
//         assertEquals("size must be between 5 and 20", v.getMessage());
//         assertEquals("honor", v.getPropertyPath().toString());
//     }

//      @Test
//     void B5807734_testHonorMustNotBeSpecialCharactor() {
//         Graduates graduates = new Graduates();
//         Date date = new Date();
// 		graduates.setGraduaId(1L);
//         graduates.setHonor("เกียรตินิยม###");
//         graduates.setGraduadate(date);
//         graduates.setGraduation("เข้ารับ");
//         graduates.setEducationlevel(educationLevel);
//         graduates.setSchoolyear(schoolYear);
//         graduates.setStudentprofile(studentProfile);

// 		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
// 		assertEquals(1, result.size());
		
//         ConstraintViolation<Graduates> v = result.iterator().next();
//         assertEquals("must match \"^[0-9A-Za-zก-์\\s]+$\"", v.getMessage());
//         assertEquals("honor", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5807734_testGraduationMustNotLessThan5() {
//         Graduates graduates = new Graduates();
//         Date date = new Date();
// 		graduates.setGraduaId(1L);
//         graduates.setHonor("เกียรตินิยม");
//         graduates.setGraduadate(date);
//         graduates.setGraduation("เข้า");
//         graduates.setEducationlevel(educationLevel);
//         graduates.setSchoolyear(schoolYear);
//         graduates.setStudentprofile(studentProfile);

// 		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
// 		assertEquals(1, result.size());
		
//         ConstraintViolation<Graduates> v = result.iterator().next();
//         assertEquals("size must be between 5 and 20", v.getMessage());
//         assertEquals("graduation", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5807734_testGraduationMustNotMoreThan20() {
//         Graduates graduates = new Graduates();
//         Date date = new Date();
// 		graduates.setGraduaId(1L);
//         graduates.setHonor("เกียรตินิยม");
//         graduates.setGraduadate(date);
//         graduates.setGraduation("มีความประสงค์จะเข้ารับ");
//         graduates.setEducationlevel(educationLevel);
//         graduates.setSchoolyear(schoolYear);
//         graduates.setStudentprofile(studentProfile);

// 		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
// 		assertEquals(1, result.size());
		
//         ConstraintViolation<Graduates> v = result.iterator().next();
//         assertEquals("size must be between 5 and 20", v.getMessage());
//         assertEquals("graduation", v.getPropertyPath().toString());
//     }

//      @Test
//     void B5807734_testGraduationMustNotBeNumberAndSpecialCharactor() {
//         Graduates graduates = new Graduates();
//         Date date = new Date();
// 		graduates.setGraduaId(1L);
//         graduates.setHonor("เกียรตินิยม");
//         graduates.setGraduadate(date);
//         graduates.setGraduation("เข้ารับ 9");
//         graduates.setEducationlevel(educationLevel);
//         graduates.setSchoolyear(schoolYear);
//         graduates.setStudentprofile(studentProfile);

// 		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
// 		assertEquals(1, result.size());
		
//         ConstraintViolation<Graduates> v = result.iterator().next();
//         assertEquals("must match \"^[A-Za-zก-์\\s]+$\"", v.getMessage());
//         assertEquals("graduation", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5807734_testStudentProfileMustNotBeNull() {
//         Graduates graduates = new Graduates();
//         Date date = new Date();
// 		graduates.setGraduaId(1L);
//         graduates.setHonor("เกียรตินิยม");
//         graduates.setGraduadate(date);
//         graduates.setGraduation("มีความ");
//         graduates.setEducationlevel(educationLevel);
//         graduates.setSchoolyear(schoolYear);
//         graduates.setStudentprofile(null);

// 		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
// 		assertEquals(1, result.size());
		
//         ConstraintViolation<Graduates> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("studentprofile", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5807734_testSchoolYearMustNotBeNull() {
//         Graduates graduates = new Graduates();
//         Date date = new Date();
// 		graduates.setGraduaId(1L);
//         graduates.setHonor("เกียรตินิยม");
//         graduates.setGraduadate(date);
//         graduates.setGraduation("มีความ");
//         graduates.setEducationlevel(educationLevel);
//         graduates.setSchoolyear(null);
//         graduates.setStudentprofile(studentProfile);

// 		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
// 		assertEquals(1, result.size());
		
//         ConstraintViolation<Graduates> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("schoolyear", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5807734_testEducationLevelMustNotBeNull() {
//         Graduates graduates = new Graduates();
//         Date date = new Date();
// 		graduates.setGraduaId(1L);
//         graduates.setHonor("เกียรตินิยม");
//         graduates.setGraduadate(date);
//         graduates.setGraduation("เข้ารับ");
//         graduates.setEducationlevel(null);
//         graduates.setSchoolyear(schoolYear);
//         graduates.setStudentprofile(studentProfile);

// 		Set<ConstraintViolation<Graduates>> result = validator.validate(graduates);
		
// 		assertEquals(1, result.size());
		
//         ConstraintViolation<Graduates> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("educationlevel", v.getPropertyPath().toString());
//     }
// }