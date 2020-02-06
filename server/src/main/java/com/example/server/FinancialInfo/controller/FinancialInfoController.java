package com.example.server.FinancialInfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.server.FinancialInfo.entity.*;
import com.example.server.FinancialInfo.repository.*;
import com.example.server.studentprofile.entity.StudentProfile;
import com.example.server.studentprofile.repository.StudentProfileRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class FinancialInfoController {

    @Autowired
    private FinancialInfoRepository financialinfoRepository;
    @Autowired
    private LevelofUseRepository levelofuseRepository;
    @Autowired
    private MoneyFormParentRepository moneyformparentRepository;
    @Autowired
    private SpendMoneyRepository spendmoneyRepository;
    @Autowired
    private StudentProfileRepository studentprofileRepository;

    FinancialInfoController(FinancialInfoRepository financialinfoRepository, LevelofUseRepository levelofuseRepository,
            MoneyFormParentRepository moneyformparentRepository, SpendMoneyRepository spendmoneyRepository,
            StudentProfileRepository studentprofileRepository) {
        this.financialinfoRepository = financialinfoRepository;
        this.levelofuseRepository = levelofuseRepository;
        this.moneyformparentRepository = moneyformparentRepository;
        this.studentprofileRepository = studentprofileRepository;
    }

    @GetMapping("/financialinfo")
    public Collection<FinancialInfo> FinancialInfo() {
        return financialinfoRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/financialinfo/{studentproId}/{academicy}/{moneyparentId}/{houseincome}/{spendId}/{levelId}/{notenough}")
    public FinancialInfo newFinancialInfo(
            @PathVariable long studentproId, @PathVariable Integer academicy, @PathVariable long moneyparentId,
            @PathVariable Integer houseincome, @PathVariable long spendId, @PathVariable long levelId,
            @PathVariable String notenough)
    {
        FinancialInfo financialinfo = new FinancialInfo();
        financialinfo.setAcademicy(academicy);
        financialinfo.setHouseincome(houseincome);
        financialinfo.setNotenough(notenough);

        StudentProfile studentprofile = studentprofileRepository.findById(studentproId);
        financialinfo.setStudentprofile(studentprofile);

        MoneyFormParent moneyformparent = moneyformparentRepository.findById(moneyparentId);
        financialinfo.setMoneyformparent(moneyformparent);

        SpendMoney spendmoney = spendmoneyRepository.findById(spendId);
        financialinfo.setSpendmoney(spendmoney);

        LevelofUse levelofuse = levelofuseRepository.findById(levelId);
        financialinfo.setLevelofuse(levelofuse);

        return financialinfoRepository.save(financialinfo);
    }
}