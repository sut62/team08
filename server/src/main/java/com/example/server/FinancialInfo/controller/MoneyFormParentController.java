package com.example.server.FinancialInfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.server.FinancialInfo.entity.MoneyFormParent;
import com.example.server.FinancialInfo.repository.MoneyFormParentRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class MoneyFormParentController {

    @Autowired
    private MoneyFormParentRepository moneyformparentRepository;

    public MoneyFormParentController(MoneyFormParentRepository moneyformparentRepository) {
        this.moneyformparentRepository = moneyformparentRepository;
    }
    @GetMapping("/moneyformparents")
    public Collection<MoneyFormParent> MoneyFormParents() {
        return moneyformparentRepository.findAll().stream().collect(Collectors.toList());
    }
}