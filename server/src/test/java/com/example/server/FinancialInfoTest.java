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

// import com.example.server.FinancialInfo.entity.*;
// import com.example.server.FinancialInfo.repository.*;
// import com.example.server.studentprofile.entity.*;
// import com.example.server.studentprofile.repository.*;

// import java.util.Optional;
// import java.util.Set;
// import java.util.Date;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;

// @DataJpaTest
// public class FinancialInfoTest {

// 	private Validator validator;
//     private StudentProfile studentProfile;
//     private Gender gender;
//     private Major major;
//     private Status status;
//     private LevelofUse levelofUse;
//     private MoneyFormParent moneyFormParent;
//     private SpendMoney spendMoney;

// 	@Autowired
//     private FinancialInfoRepository financialinfoRepository;
    
//     @Autowired
//     private MoneyFormParentRepository moneyFormParentRepository;

//     @Autowired
//     private LevelofUseRepository levelofUseRepository;

//     @Autowired
//     private SpendMoneyRepository spendMoneyRepository;

//     @Autowired
//     private StudentProfileRepository studentProfileRepository;
    
//     @Autowired
//     private GenderRepository genderRepository;

//     @Autowired
//     private MajorRepository majorRepository;

//     @Autowired
//     private StatusRepository statusRepository;

// 	@BeforeEach
// 	public void setup() {
// 		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//         validator = factory.getValidator();

//         spendMoney = new SpendMoney();
//         spendMoney.setSpendId(1L);
//         spendMoney.setUsemoney("2000-3000");
//         spendMoney = spendMoneyRepository.saveAndFlush(spendMoney);

//         levelofUse = new LevelofUse();
//         levelofUse.setLevelId(1L);
//         levelofUse.setUsed("พอใช้");
//         levelofUse = levelofUseRepository.saveAndFlush(levelofUse);

//         moneyFormParent = new MoneyFormParent();
//         moneyFormParent.setMoneyparentId(1L);
//         moneyFormParent.setParent("บิดา");
//         moneyFormParent = moneyFormParentRepository.saveAndFlush(moneyFormParent);

//         gender = new Gender();
//         gender.setGender("ผู้หญิง");
//         gender.setGenderId(8L);
//         gender = genderRepository.saveAndFlush(gender);

//         major = new Major();
//         major.setMajor("วิศวกรรมศาสตร์");
//         major.setMajorId(8L);
//         major = majorRepository.saveAndFlush(major);

//         status = new Status();
//         status.setStatus("โสด");
//         status.setStatusId(8L);
//         status = statusRepository.saveAndFlush(status);

//         studentProfile = new StudentProfile();
//         Date date = new Date();
//         studentProfile.setStudentproId(94L);
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
//     void B5812431_testCreatedFinancialInfoOK() {
//         FinancialInfo financialinfo = new FinancialInfo();
//         financialinfo.setFinanId(1L);
//         financialinfo.setAcademicy(2562);
//         financialinfo.setHouseincome(50000);
//         financialinfo.setNotenough("aa");
//         financialinfo.setLevelofuse(levelofUse);
//         financialinfo.setStudentprofile(studentProfile);
//         financialinfo.setMoneyformparent(moneyFormParent);
//         financialinfo.setSpendmoney(spendMoney);

//         financialinfo = financialinfoRepository.saveAndFlush(financialinfo);

//         Optional<FinancialInfo> FinancialInfoCreated = financialinfoRepository
//                 .findById(financialinfo.getFinanId());
//         assertEquals(1L, FinancialInfoCreated.get().getFinanId());
//         assertEquals(2562, FinancialInfoCreated.get().getAcademicy());
//         assertEquals(50000, FinancialInfoCreated.get().getHouseincome());
//         assertEquals("aa", FinancialInfoCreated.get().getNotenough());
//         assertEquals(levelofUse, FinancialInfoCreated.get().getLevelofuse());
//         assertEquals(studentProfile, FinancialInfoCreated.get().getStudentprofile());
//         assertEquals(spendMoney, FinancialInfoCreated.get().getSpendmoney());
//         assertEquals(moneyFormParent, FinancialInfoCreated.get().getMoneyformparent());
//     }
   
//     // academicy กรณีที่ 1 ชื่อต้องไม่เป็นค่าว่าง
//     @Test
//     void B5812431_testAcademicyMustNotBeNull() {
//         FinancialInfo financialinfo = new FinancialInfo();
//         financialinfo.setFinanId(1L);
//         financialinfo.setAcademicy(null);
//         financialinfo.setHouseincome(50000);
//         financialinfo.setNotenough("aa");
//         financialinfo.setLevelofuse(levelofUse);
//         financialinfo.setStudentprofile(studentProfile);
//         financialinfo.setMoneyformparent(moneyFormParent);
//         financialinfo.setSpendmoney(spendMoney);

//         Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FinancialInfo> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("academicy", v.getPropertyPath().toString());
//     }

    
//     @Test
//     void B5812431_testHouseincomeMustNotLessThan20000() {
//         FinancialInfo financialinfo = new FinancialInfo();
//         financialinfo.setFinanId(1L);
//         financialinfo.setAcademicy(2562);
//         financialinfo.setHouseincome(19999);
//         financialinfo.setNotenough("aa");
//         financialinfo.setLevelofuse(levelofUse);
//         financialinfo.setStudentprofile(studentProfile);
//         financialinfo.setMoneyformparent(moneyFormParent);
//         financialinfo.setSpendmoney(spendMoney);

//         Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FinancialInfo> v = result.iterator().next();
//         assertEquals("must be greater than or equal to 20000", v.getMessage());
//         assertEquals("houseincome", v.getPropertyPath().toString());
//     }

    
//     @Test
//     void B5812431_testHouseincomeMustNotMoreThan100000() {
//         FinancialInfo financialinfo = new FinancialInfo();
//         financialinfo.setFinanId(1L);
//         financialinfo.setAcademicy(2562);
//         financialinfo.setHouseincome(100001);
//         financialinfo.setNotenough("aa");
//         financialinfo.setLevelofuse(levelofUse);
//         financialinfo.setStudentprofile(studentProfile);
//         financialinfo.setMoneyformparent(moneyFormParent);
//         financialinfo.setSpendmoney(spendMoney);

//         Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FinancialInfo> v = result.iterator().next();
//         assertEquals("must be less than or equal to 100000", v.getMessage());
//         assertEquals("houseincome", v.getPropertyPath().toString());
//     }

//     // Houseincome กรณีที่ 3 ชื่อต้องไม่เป็นค่าว่าง
//     @Test
//     void B5812431_testHouseincomeMustNotBeNull() {
//         FinancialInfo financialinfo = new FinancialInfo();
//         financialinfo.setFinanId(1L);
//         financialinfo.setAcademicy(2562);
//         financialinfo.setHouseincome(null);
//         financialinfo.setNotenough("aa");
//         financialinfo.setLevelofuse(levelofUse);
//         financialinfo.setStudentprofile(studentProfile);
//         financialinfo.setMoneyformparent(moneyFormParent);
//         financialinfo.setSpendmoney(spendMoney);

//         Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FinancialInfo> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("houseincome", v.getPropertyPath().toString());
//     }

//     // Notenough กรณีที่ 1 น้อยกว่า 2 ตัว
//     @Test
//     void B5812431_testNotenoughSizeLessThan2() {
//         FinancialInfo financialinfo = new FinancialInfo();
//         financialinfo.setFinanId(1L);
//         financialinfo.setAcademicy(2562);
//         financialinfo.setHouseincome(50000);
//         financialinfo.setNotenough("a");
//         financialinfo.setLevelofuse(levelofUse);
//         financialinfo.setStudentprofile(studentProfile);
//         financialinfo.setMoneyformparent(moneyFormParent);
//         financialinfo.setSpendmoney(spendMoney);

//         Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FinancialInfo> v = result.iterator().next();
//         assertEquals("size must be between 2 and 20", v.getMessage());
//         assertEquals("notenough", v.getPropertyPath().toString());
//     }

//     // Notenough กรณีที่ 1 มากกว่า 20 ตัว
//     @Test
//     void B5812431_testNotenoughSizeMorethan20() {
//         FinancialInfo financialinfo = new FinancialInfo();
//         financialinfo.setFinanId(1L);
//         financialinfo.setAcademicy(2562);
//         financialinfo.setHouseincome(50000);
//         financialinfo.setNotenough("aaaaaaaaaaaaaaaaaaaaa");
//         financialinfo.setLevelofuse(levelofUse);
//         financialinfo.setStudentprofile(studentProfile);
//         financialinfo.setMoneyformparent(moneyFormParent);
//         financialinfo.setSpendmoney(spendMoney);

//         Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FinancialInfo> v = result.iterator().next();
//         assertEquals("size must be between 2 and 20", v.getMessage());
//         assertEquals("notenough", v.getPropertyPath().toString());
//     }


//     // Notenough กรณีที่ 3 ชื่อต้องไม่เป็นค่าว่าง
//     @Test
//     void B5812431_testLevelofUseMustNotBeNull() {
//         FinancialInfo financialinfo = new FinancialInfo();
//         financialinfo.setFinanId(1L);
//         financialinfo.setAcademicy(2562);
//         financialinfo.setHouseincome(50000);
//         financialinfo.setNotenough("note");
//         financialinfo.setLevelofuse(null);
//         financialinfo.setStudentprofile(studentProfile);
//         financialinfo.setMoneyformparent(moneyFormParent);
//         financialinfo.setSpendmoney(spendMoney);

//         Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FinancialInfo> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("levelofuse", v.getPropertyPath().toString());
//     }
    
//     @Test
//     void B5812431_testStudentProfileMustNotBeNull() {
//         FinancialInfo financialinfo = new FinancialInfo();
//         financialinfo.setFinanId(1L);
//         financialinfo.setAcademicy(2562);
//         financialinfo.setHouseincome(50000);
//         financialinfo.setNotenough("note");
//         financialinfo.setLevelofuse(levelofUse);
//         financialinfo.setStudentprofile(null);
//         financialinfo.setMoneyformparent(moneyFormParent);
//         financialinfo.setSpendmoney(spendMoney);

//         Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FinancialInfo> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("studentprofile", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5812431_testMoneyFormParentMustNotBeNull() {
//         FinancialInfo financialinfo = new FinancialInfo();
//         financialinfo.setFinanId(1L);
//         financialinfo.setAcademicy(2562);
//         financialinfo.setHouseincome(50000);
//         financialinfo.setNotenough("note");
//         financialinfo.setLevelofuse(levelofUse);
//         financialinfo.setStudentprofile(studentProfile);
//         financialinfo.setMoneyformparent(null);
//         financialinfo.setSpendmoney(spendMoney);

//         Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FinancialInfo> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("moneyformparent", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5812431_testSpendMoneyMustNotBeNull() {
//         FinancialInfo financialinfo = new FinancialInfo();
//         financialinfo.setFinanId(1L);
//         financialinfo.setAcademicy(2562);
//         financialinfo.setHouseincome(50000);
//         financialinfo.setNotenough("note");
//         financialinfo.setLevelofuse(levelofUse);
//         financialinfo.setStudentprofile(studentProfile);
//         financialinfo.setMoneyformparent(moneyFormParent);
//         financialinfo.setSpendmoney(null);

//         Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FinancialInfo> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("spendmoney", v.getPropertyPath().toString());
//     }

//     @Test
//     void B5812431_testNotenoughMustNotBeNull() {
//         FinancialInfo financialinfo = new FinancialInfo();
//         financialinfo.setFinanId(1L);
//         financialinfo.setAcademicy(2562);
//         financialinfo.setHouseincome(50000);
//         financialinfo.setNotenough(null);
//         financialinfo.setLevelofuse(levelofUse);
//         financialinfo.setStudentprofile(studentProfile);
//         financialinfo.setMoneyformparent(moneyFormParent);
//         financialinfo.setSpendmoney(spendMoney);

//         Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FinancialInfo> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("notenough", v.getPropertyPath().toString());
//     }
// }
