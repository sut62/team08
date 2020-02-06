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

import com.example.server.FinancialInfo.entity.FinancialInfo;
import com.example.server.FinancialInfo.repository.FinancialInfoRepository;
import com.example.server.studentprofile.entity.StudentProfile;

import java.util.Optional;
import java.util.Set;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class FinancialInfoTest {

	private Validator validator;

	@Autowired
	private FinancialInfoRepository financialinfoRepository;

	@BeforeEach
	public void setup() {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5812431_testCreatedFinancialInfoOK() {
        FinancialInfo financialinfo = new FinancialInfo();
        financialinfo.setFinanId(1L);
        financialinfo.setAcademicy(2562);
        financialinfo.setHouseincome(50000);
        financialinfo.setNotenough("aa");

        financialinfo = financialinfoRepository.saveAndFlush(financialinfo);

        Optional<FinancialInfo> FinancialInfoCreated = financialinfoRepository
                .findById(financialinfo.getFinanId());
        assertEquals(1L, FinancialInfoCreated.get().getFinanId());
        assertEquals(2562, FinancialInfoCreated.get().getAcademicy());
        assertEquals(50000, FinancialInfoCreated.get().getHouseincome());
        assertEquals("aa", FinancialInfoCreated.get().getNotenough());
    
    }
   
    // academicy กรณีที่ 1 ชื่อต้องไม่เป็นค่าว่าง
    @Test
    void B5812431_testAcademicyMustNotBeNull() {
        FinancialInfo financialinfo = new FinancialInfo();
        financialinfo.setFinanId(1L);
        financialinfo.setAcademicy(null);
        financialinfo.setHouseincome(50000);
        financialinfo.setNotenough("aa");

        Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FinancialInfo> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("academicy", v.getPropertyPath().toString());
    }

    
    @Test
    void B5812431_testHouseincomeMustNotLessThan20000() {
        FinancialInfo financialinfo = new FinancialInfo();
        financialinfo.setFinanId(1L);
        financialinfo.setAcademicy(2562);
        financialinfo.setHouseincome(19999);
        financialinfo.setNotenough("aa");

        Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FinancialInfo> v = result.iterator().next();
        assertEquals("must be greater than or equal to 20000", v.getMessage());
        assertEquals("houseincome", v.getPropertyPath().toString());
    }

    
    @Test
    void B5812431_testHouseincomeMustNotMoreThan100000() {
        FinancialInfo financialinfo = new FinancialInfo();
        financialinfo.setFinanId(1L);
        financialinfo.setAcademicy(2562);
        financialinfo.setHouseincome(100001);
        financialinfo.setNotenough("aa");

        Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FinancialInfo> v = result.iterator().next();
        assertEquals("must be less than or equal to 100000", v.getMessage());
        assertEquals("houseincome", v.getPropertyPath().toString());
    }

    // Houseincome กรณีที่ 3 ชื่อต้องไม่เป็นค่าว่าง
    @Test
    void B5812431_testHouseincomeMustNotBeNull() {
        FinancialInfo financialinfo = new FinancialInfo();
        financialinfo.setFinanId(1L);
        financialinfo.setAcademicy(2562);
        financialinfo.setHouseincome(null);
        financialinfo.setNotenough("aa");

        Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FinancialInfo> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("houseincome", v.getPropertyPath().toString());
    }

    // Notenough กรณีที่ 1 น้อยกว่า 2 ตัว
    @Test
    void B5812431_testNotenoughSizeLessThan2() {
        FinancialInfo financialinfo = new FinancialInfo();
        financialinfo.setFinanId(1L);
        financialinfo.setAcademicy(2562);
        financialinfo.setHouseincome(50000);
        financialinfo.setNotenough("a");


        Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FinancialInfo> v = result.iterator().next();
        assertEquals("size must be between 2 and 20", v.getMessage());
        assertEquals("notenough", v.getPropertyPath().toString());
    }

    // Notenough กรณีที่ 1 มากกว่า 20 ตัว
    @Test
    void B5812431_testNotenoughSizeMorethan20() {
        FinancialInfo financialinfo = new FinancialInfo();
        financialinfo.setFinanId(1L);
        financialinfo.setAcademicy(2562);
        financialinfo.setHouseincome(50000);
        financialinfo.setNotenough("aaaaaaaaaaaaaaaaaaaaa");


        Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FinancialInfo> v = result.iterator().next();
        assertEquals("size must be between 2 and 20", v.getMessage());
        assertEquals("notenough", v.getPropertyPath().toString());
    }


    // Notenough กรณีที่ 3 ชื่อต้องไม่เป็นค่าว่าง
    @Test
    void B5812431_testNotenoughMustNotBeNull() {
        FinancialInfo financialinfo = new FinancialInfo();
        financialinfo.setFinanId(1L);
        financialinfo.setAcademicy(2562);
        financialinfo.setHouseincome(50000);
        financialinfo.setNotenough(null);


        Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FinancialInfo> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("notenough", v.getPropertyPath().toString());
    }
}
