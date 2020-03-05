 package com.example.server;

 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
 import org.springframework.dao.DataIntegrityViolationException;

 import javax.validation.ConstraintViolation;
 import javax.validation.Validation;
 import javax.validation.Validator;
 import javax.validation.ValidatorFactory;

 import com.example.server.StudentHealthRecord.entity.*;
 import com.example.server.StudentHealthRecord.repository.*;
 import com.example.server.studentprofile.entity.*;
 import com.example.server.studentprofile.repository.*;

 import java.util.Date;
 import java.util.Optional;
 import java.util.Set;

 import static org.junit.jupiter.api.Assertions.assertEquals;
 import static org.junit.jupiter.api.Assertions.assertThrows;

 @DataJpaTest
 public class StudentHealthRecordTest {

     private Validator validator;
     private StudentProfile studentProfile;
     private Gender gender;
    private Major major;
    private Status status;
     private DrugAllergyHistory drugAllergyHistory;
     private CongenitalDisease congenitalDisease;

     @Autowired
     private StudentHealthRecordRepository StudentHealthRecordRepository;

     @Autowired
     private DrugAllergyHistoryRepository drugAllergyHistoryRepository;

     @Autowired
     private CongenitalDiseaseRepository congenitalDiseaseRepository;

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
         ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
         validator = factory.getValidator();

         drugAllergyHistory = new DrugAllergyHistory();
         drugAllergyHistory.setDrugallergyhistory("ยาคลายกล้ามเนื้อ");
         drugAllergyHistory.setDrugallergyhistoryid(1L);
         drugAllergyHistory = drugAllergyHistoryRepository.saveAndFlush(drugAllergyHistory);

         congenitalDisease = new CongenitalDisease();
        congenitalDisease.setCongenitaldiseaseid(1L);
         congenitalDisease.setCongenitaldisease("โรคภูมิแพ้อากาศ");
         congenitalDisease = congenitalDiseaseRepository.saveAndFlush(congenitalDisease);

         gender = new Gender();
         gender.setGender("ผู้หญิง");
         gender.setGenderId(4L);
         gender = genderRepository.saveAndFlush(gender);

         major = new Major();
         major.setMajor("วิศวกรรมศาสตร์");
         major.setMajorId(4L);
         major = majorRepository.saveAndFlush(major);

         status = new Status();
         status.setStatus("โสด");
         status.setStatusId(4L);
         status = statusRepository.saveAndFlush(status);

         studentProfile = new StudentProfile();
         Date date = new Date();
         studentProfile.setStudentproId(96L);
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

     @Test 
     void B5828104_testCreateStudentHealthRecordOK() {
         StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
         studentHealthRecord.setStudentHealthRecordid(1L);
         studentHealthRecord.setHeight(180);
         studentHealthRecord.setWeight(60);
         studentHealthRecord.setCongenitaldisease(congenitalDisease);
         studentHealthRecord.setStudentprofile(studentProfile);
         studentHealthRecord.setDrugallergyhistory(drugAllergyHistory);

         studentHealthRecord = StudentHealthRecordRepository.saveAndFlush(studentHealthRecord);

         Optional<StudentHealthRecord> Result = StudentHealthRecordRepository.findById(studentHealthRecord.getStudentHealthRecordid());
         assertEquals(1L, Result.get().getStudentHealthRecordid());
         assertEquals(180, Result.get().getHeight());
         assertEquals(60, Result.get().getWeight());
         assertEquals(congenitalDisease, Result.get().getCongenitaldisease());
         assertEquals(studentProfile, Result.get().getStudentprofile());
         assertEquals(drugAllergyHistory, Result.get().getDrugallergyhistory());
     }

     @Test 
     void B5828104_testStudentHealthRecordIdMustNotBeNull() {
         StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
         studentHealthRecord.setStudentHealthRecordid(null);
         studentHealthRecord.setHeight(180);
         studentHealthRecord.setWeight(60);
         studentHealthRecord.setCongenitaldisease(congenitalDisease);
         studentHealthRecord.setStudentprofile(studentProfile);
         studentHealthRecord.setDrugallergyhistory(drugAllergyHistory);

         Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);
         assertEquals(1, result.size());
         ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
         assertEquals("must not be null", v.getMessage());
         assertEquals("StudentHealthRecordid", v.getPropertyPath().toString());
     }


     @Test 
     void B5828104_testHeightMustNotBeNull() {
         StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
         studentHealthRecord.setStudentHealthRecordid(1L);
         studentHealthRecord.setHeight(null);
         studentHealthRecord.setWeight(60);
         studentHealthRecord.setCongenitaldisease(congenitalDisease);
         studentHealthRecord.setStudentprofile(studentProfile);
         studentHealthRecord.setDrugallergyhistory(drugAllergyHistory);

         Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);
         assertEquals(1, result.size());

         ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
         assertEquals("must not be null", v.getMessage());
         assertEquals("height", v.getPropertyPath().toString());
     }

     @Test 
     void B5828104_testWidthMustNotBeNull() {
         StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
         studentHealthRecord.setStudentHealthRecordid(1L);
         studentHealthRecord.setHeight(180);
         studentHealthRecord.setWeight(null);
         studentHealthRecord.setCongenitaldisease(congenitalDisease);
         studentHealthRecord.setStudentprofile(studentProfile);
         studentHealthRecord.setDrugallergyhistory(drugAllergyHistory);
         Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);

         assertEquals(1, result.size());

         ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
         assertEquals("must not be null", v.getMessage());
         assertEquals("weight", v.getPropertyPath().toString());
     }

     @Test 
     void B5828104_testHeightMustNotLessThan100() {
         StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
         studentHealthRecord.setStudentHealthRecordid(1L);
         studentHealthRecord.setHeight(99);
         studentHealthRecord.setWeight(60);
         studentHealthRecord.setCongenitaldisease(congenitalDisease);
         studentHealthRecord.setStudentprofile(studentProfile);
         studentHealthRecord.setDrugallergyhistory(drugAllergyHistory);

         Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);
         assertEquals(1, result.size());
         ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
         assertEquals("must be greater than or equal to 100", v.getMessage());
         assertEquals("height", v.getPropertyPath().toString());
     }

     @Test 
     void B5828104_testHeightMustNotMoreThan220() {
         StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
         studentHealthRecord.setStudentHealthRecordid(1L);
         studentHealthRecord.setHeight(221);
         studentHealthRecord.setWeight(60);
         studentHealthRecord.setCongenitaldisease(congenitalDisease);
         studentHealthRecord.setStudentprofile(studentProfile);
         studentHealthRecord.setDrugallergyhistory(drugAllergyHistory);

         Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);
         assertEquals(1, result.size());
         ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
         assertEquals("must be less than or equal to 220", v.getMessage());
         assertEquals("height", v.getPropertyPath().toString());
     }

     @Test 
     void B5828104_testWeightMustNotLessThan30() {
         StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
         studentHealthRecord.setStudentHealthRecordid(1L);
         studentHealthRecord.setHeight(180);
         studentHealthRecord.setWeight(29);
         studentHealthRecord.setCongenitaldisease(congenitalDisease);
         studentHealthRecord.setStudentprofile(studentProfile);
         studentHealthRecord.setDrugallergyhistory(drugAllergyHistory);
         Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);

         assertEquals(1, result.size());

         ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
         assertEquals("must be greater than or equal to 30", v.getMessage());
         assertEquals("weight", v.getPropertyPath().toString());
     }

     @Test 
     void B5828104_testWeightMustNotMoreThan200() {
         StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
         studentHealthRecord.setStudentHealthRecordid(1L);
         studentHealthRecord.setHeight(180);
         studentHealthRecord.setWeight(201);
         studentHealthRecord.setCongenitaldisease(congenitalDisease);
         studentHealthRecord.setStudentprofile(studentProfile);
         studentHealthRecord.setDrugallergyhistory(drugAllergyHistory);

         Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);

         assertEquals(1, result.size());
         ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
         assertEquals("must be less than or equal to 200", v.getMessage());
         assertEquals("weight", v.getPropertyPath().toString());
     }

     @Test 
     void B5828104_testDrugAllergyHistoryMustNotBeNull() {
         StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
         studentHealthRecord.setStudentHealthRecordid(1L);
         studentHealthRecord.setHeight(180);
         studentHealthRecord.setWeight(200);
         studentHealthRecord.setCongenitaldisease(congenitalDisease);
         studentHealthRecord.setStudentprofile(studentProfile);
         studentHealthRecord.setDrugallergyhistory(null);

         Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);

         assertEquals(1, result.size());

         ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
         assertEquals("must not be null", v.getMessage());
         assertEquals("drugallergyhistory", v.getPropertyPath().toString());
     }

     @Test 
    void B5828104_testCongenitalDiseaseMustNotBeNull() {
         StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
         studentHealthRecord.setStudentHealthRecordid(1L);
         studentHealthRecord.setHeight(180);
         studentHealthRecord.setWeight(200);
         studentHealthRecord.setCongenitaldisease(null);
         studentHealthRecord.setStudentprofile(studentProfile);
         studentHealthRecord.setDrugallergyhistory(drugAllergyHistory);
         Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);

         assertEquals(1, result.size());
         ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
         assertEquals("must not be null", v.getMessage());
         assertEquals("congenitaldisease", v.getPropertyPath().toString());
     }

     @Test 
     void B5828104_testStudentProfileMustNotBeNull() {
         StudentHealthRecord studentHealthRecord = new StudentHealthRecord();
         studentHealthRecord.setStudentHealthRecordid(1L);
         studentHealthRecord.setHeight(180);
         studentHealthRecord.setWeight(200);
         studentHealthRecord.setCongenitaldisease(congenitalDisease);
         studentHealthRecord.setStudentprofile(null);
         studentHealthRecord.setDrugallergyhistory(drugAllergyHistory);

         Set<ConstraintViolation<StudentHealthRecord>> result = validator.validate(studentHealthRecord);

         assertEquals(1, result.size());
         ConstraintViolation<StudentHealthRecord> v = result.iterator().next();
         assertEquals("must not be null", v.getMessage());
         assertEquals("studentprofile", v.getPropertyPath().toString());
     }
 }