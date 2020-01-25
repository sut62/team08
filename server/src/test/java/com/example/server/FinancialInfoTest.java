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
        financialinfo.setAcademicy("1234");
        financialinfo.setHouseincome("12345");
        financialinfo.setNotenough("aa");

        financialinfo = financialinfoRepository.saveAndFlush(financialinfo);

        Optional<FinancialInfo> FinancialInfoCreated = financialinfoRepository
                .findById(financialinfo.getFinanId());
        assertEquals(1L, FinancialInfoCreated.get().getFinanId());
        assertEquals("1234", FinancialInfoCreated.get().getAcademicy());
        assertEquals("12345", FinancialInfoCreated.get().getHouseincome());
        assertEquals("aa", FinancialInfoCreated.get().getNotenough());
    
    }
    // academicy กรณีที่ 2 น้อยกว่า 4 ตัว
    @Test
    void B5812431_testAcademicySizeLessThan4() {
        FinancialInfo financialinfo = new FinancialInfo();
        financialinfo.setFinanId(1L);
        financialinfo.setAcademicy("123");
        financialinfo.setHouseincome("12345");
        financialinfo.setNotenough("aa");

        Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FinancialInfo> v = result.iterator().next();
        assertEquals("must match \"\\d{4}\"", v.getMessage());
        assertEquals("academicy", v.getPropertyPath().toString());
    }

    // academicy กรณีที่ 3 มากกว่า 4 ตัว
    @Test
    void B5812431_testAcademicySizeMorethan5() {
        FinancialInfo financialinfo = new FinancialInfo();
        financialinfo.setFinanId(1L);
        financialinfo.setAcademicy("12345");
        financialinfo.setHouseincome("12345");
        financialinfo.setNotenough("aa");

        Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FinancialInfo> v = result.iterator().next();
        assertEquals("must match \"\\d{4}\"", v.getMessage());
        assertEquals("academicy", v.getPropertyPath().toString());
    }

    // academicy กรณีที่ 4 ชื่อต้องไม่เป็นค่าว่าง
    @Test
    void B5812431_testAcademicyMustNotBeNull() {
        FinancialInfo financialinfo = new FinancialInfo();
        financialinfo.setFinanId(1L);
        financialinfo.setAcademicy(null);
        financialinfo.setHouseincome("12345");
        financialinfo.setNotenough("aa");

        Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FinancialInfo> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("academicy", v.getPropertyPath().toString());
    }

    // Houseincome กรณีที่ 1 น้อยกว่า 5 ตัว
    @Test
    void B5812431_testHouseincomeSizeLessThan5() {
        FinancialInfo financialinfo = new FinancialInfo();
        financialinfo.setFinanId(1L);
        financialinfo.setAcademicy("1234");
        financialinfo.setHouseincome("1234");
        financialinfo.setNotenough("aa");

        Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FinancialInfo> v = result.iterator().next();
        assertEquals("size must be between 5 and 10", v.getMessage());
        assertEquals("houseincome", v.getPropertyPath().toString());
    }

    // Houseincome กรณีที่ 2 มากกว่า 10 ตัว
    @Test
    void B5812431_testHouseincomeSizeMorethan10() {
        FinancialInfo financialinfo = new FinancialInfo();
        financialinfo.setFinanId(1L);
        financialinfo.setAcademicy("1234");
        financialinfo.setHouseincome("12345678901");
        financialinfo.setNotenough("aa");

        Set<ConstraintViolation<FinancialInfo>> result = validator.validate(financialinfo);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FinancialInfo> v = result.iterator().next();
        assertEquals("size must be between 5 and 10", v.getMessage());
        assertEquals("houseincome", v.getPropertyPath().toString());
    }

    // Houseincome กรณีที่ 3 ชื่อต้องไม่เป็นค่าว่าง
    @Test
    void B5812431_testHouseincomeMustNotBeNull() {
        FinancialInfo financialinfo = new FinancialInfo();
        financialinfo.setFinanId(1L);
        financialinfo.setAcademicy("1234");
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
        financialinfo.setAcademicy("1234");
        financialinfo.setHouseincome("12345");
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
        financialinfo.setAcademicy("1234");
        financialinfo.setHouseincome("12345");
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
        financialinfo.setAcademicy("1234");
        financialinfo.setHouseincome("12345");
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
