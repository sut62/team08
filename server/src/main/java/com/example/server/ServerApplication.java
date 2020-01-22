package com.example.server;

import com.example.server.studentprofile.entity.*;
import com.example.server.studentprofile.repository.*;
import com.example.server.Discipline.entity.Breakrule;
import com.example.server.Discipline.entity.Punish;
import com.example.server.Discipline.repository.BreakruleRepository;
import com.example.server.Discipline.repository.PunishRepository;
import com.example.server.FinancialInfo.repository.*;
import com.example.server.Scholarship.entity.ScholarshipOfficer;
import com.example.server.Scholarship.entity.ScholarshipType;
import com.example.server.Scholarship.repository.ScholarshipOfficerRepository;
import com.example.server.Scholarship.repository.ScholarshipTypeRepository;
import com.example.server.StudentHealthRecord.entity.CongenitalDisease;
import com.example.server.StudentHealthRecord.entity.DrugAllergyHistory;
import com.example.server.StudentHealthRecord.repository.*;
import com.example.server.Activities.entity.Institution;
import com.example.server.Activities.entity.SchoolYear;
import com.example.server.Activities.repository.InstitutionRepository;
import com.example.server.Activities.repository.SchoolYearRepository;
import com.example.server.Activities.repository.ActivitiesRepository;
import com.example.server.FinancialInfo.entity.*;
import com.example.server.studentprofile.repository.GenderRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	ApplicationRunner init(GenderRepository genderRepository, MajorRepository majorRepository,
						   StatusRepository statusRepository, StudentProfileRepository studentprofileRepository,
						   ActivitiesRepository activitiesRepository, InstitutionRepository institutionRepository,
						   BreakruleRepository breakruleRepository, PunishRepository punishRepository,
						   ScholarshipOfficerRepository scholarshipOfficerRepository, SchoolYearRepository schoolyearRepository,
						   LevelofUseRepository levelofuseRepository, ScholarshipTypeRepository scholarshipTypeRepository,
						   MoneyFormParentRepository moneyformparentRepository, SpendMoneyRepository spendmoneyRepository,
						   CongenitalDiseaseRepository congenitalDiseaseRepository,
						   DrugAllergyHistoryRepository drugAllergyHistoryRepository) {
		return args -> {
			Stream.of("ผู้หญิง", "ผู้ชาย", "อื่นๆ").forEach(sex -> {
				Gender gender = new Gender();
				gender.setGender(sex);
				genderRepository.save(gender);
			});
			Stream.of("วิศวกรรมศาสตร์", "วิทย์กีฬา", "เภสัชกร", "แพทย์").forEach(ma -> {
				Major major = new Major();
				major.setMajor(ma);
				majorRepository.save(major);
			});
			Stream.of("โสด", "สมรส", "หย่าร้าง").forEach(status -> {
				Status s = new Status();
				s.setStatus(status);
				statusRepository.save(s);
			});

			Stream.of(2560, 2561, 2562).forEach(year -> {
				SchoolYear schoolyear = new SchoolYear();
				schoolyear.setScyear(year);
				schoolyearRepository.save(schoolyear);
			});
			Stream.of("อาคารเรียนรวม 1", "อาคารเรียนรวม 2").forEach(ins -> {
				Institution institution = new Institution();
				institution.setInsname(ins);
				institutionRepository.save(institution);
			});

			StudentProfile s = new StudentProfile();
			s.setNameeng("Test Name");
			s.setNamethai("ทดสอบ ชื่อ");
			s.setIdcard("B60XXXXX");
			s.setIdnumber("1234567890123");
			s.setAddress("address");
			s.setAge(22);
			s.setBrithday(new Date());
			s.setBlood("AB");
			s.setTel("0834567890");
			studentprofileRepository.save(s);

			Stream.of("เล่นการพนัน", "ขโมยทรัพย์สิน", "ทำร้ายร่างกาย", "ทุจริต", "วินัยจราจร").forEach(ruleType -> {
				Breakrule breakrule = new Breakrule(); // สร้าง Object Breakrule
				breakrule.setRuleType(ruleType); // set ชื่อ (name) ให้ Object ชื่อ Breakrule
				breakruleRepository.save(breakrule); // บันทึก Objcet ชื่อ Breakrule
			});

			Stream.of("ตักเตือนเป็นลายลักษณ์อักษร", "ภาคทัณฑ์", "พักการเรียน", "พ้นสภาพนักศึกษา")
					.forEach(punishType -> {
						Punish punish = new Punish(); // สร้าง Object Punish
						punish.setPunishType(punishType); // set ชื่อ (name) ให้ Object ชื่อ Punish
						punishRepository.save(punish); // บันทึก Objcet ชื่อ Punish
					});

			Stream.of("บิดา", "มารดา", "ญาติ").forEach(parent -> {
				MoneyFormParent moneyformparent = new MoneyFormParent(); // สร้าง Object MoneyFormParent
				moneyformparent.setParent(parent); // set ชื่อ (name) ให้ Object ชื่อ MoneyFormParent
				moneyformparentRepository.save(moneyformparent); // บันทึก Objcet ชื่อ MoneyFormParent
			});

			Stream.of("2000-3000", "3001-4000", "4001-5000").forEach(usemoney -> {
				SpendMoney spendmoney = new SpendMoney(); // สร้าง Object SpendMoney
				spendmoney.setUsemoney(usemoney); // set ชื่อ (name) ให้ Object ชื่อ SpendMoney
				spendmoneyRepository.save(spendmoney); // บันทึก Objcet ชื่อ SpendMoney
			});

			Stream.of("พอใช้", "ไม่พอใช้", "กำลังดี").forEach(used -> {
				LevelofUse levelofuse = new LevelofUse(); // สร้าง Object LevelofUse
				levelofuse.setUsed(used); // set ชื่อ (name) ให้ Object ชื่อ LevelofUse
				levelofuseRepository.save(levelofuse); // บันทึก Objcet ชื่อ LevelofUse
			});

			Stream.of("ยาคลายกล้ามเนื้อ", "ยาแก้แพ้อากาศ").forEach(drugallergyhistory -> {
				DrugAllergyHistory drugAllergyHistory = new DrugAllergyHistory();
				drugAllergyHistory.setDrugallergyhistory(drugallergyhistory);
				drugAllergyHistoryRepository.save(drugAllergyHistory);
			});

			Stream.of("โรคภูมิแพ้อากาศ", "โรคกล้ามเนื้ออ่อนแรง").forEach(congenitaldisease -> {
				CongenitalDisease congenitalDisease = new CongenitalDisease();
				congenitalDisease.setCongenitaldisease(congenitaldisease);
				congenitalDiseaseRepository.save(congenitalDisease);
			});

			Stream.of("ทุนศักยบันณฑิต", "ทุน 84 พรรษา", "ทุนกู้ยืมการศึกษา").forEach(scholarshipType -> {
				ScholarshipType scholarshiptype = new ScholarshipType();
				scholarshiptype.setGoodEducation(scholarshipType);
				scholarshiptype.setEducationalLoans(scholarshipType);
				scholarshipTypeRepository.save(scholarshiptype);
			});

			Stream.of("พนักงาน งานทุน1", "พนักงาน งานทุน2", "พนักงาน งานทุน3").forEach(officer -> {
				ScholarshipOfficer scholarshipOfficer = new ScholarshipOfficer();
				scholarshipOfficer.setOfficername(officer);
				scholarshipOfficerRepository.save(scholarshipOfficer);
			});
		};
	}
}