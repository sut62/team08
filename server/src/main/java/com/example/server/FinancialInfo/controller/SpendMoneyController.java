package com.example.server.FinancialInfo.controller;

import com.example.server.FinancialInfo.entity.SpendMoney;
import com.example.server.FinancialInfo.repository.SpendMoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class SpendMoneyController {

    @Autowired
    private SpendMoneyRepository spendmoneyRepository;

    public SpendMoneyController(SpendMoneyRepository spendmoneyRepository) {
        this.spendmoneyRepository = spendmoneyRepository;
    }
    @GetMapping("/spendmoneys")
    public Collection<SpendMoney> LevelofUses() {
        return spendmoneyRepository.findAll().stream().collect(Collectors.toList());
    }
}