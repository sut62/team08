package com.example.server.Discipline.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
// import java.util.Date;
import java.util.stream.Collectors;

import com.example.server.Discipline.repository.*;
import com.example.server.studentprofile.entity.StudentProfile;
import com.example.server.studentprofile.repository.StudentProfileRepository;
import com.example.server.Discipline.entity.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class DisciplineController {

    @Autowired
    private DisciplineRepository disciplineRepository;
    @Autowired
    private BreakruleRepository breakruleRepository;
    @Autowired
    private PunishRepository punishRepository;
    @Autowired
    private StudentProfileRepository studentprofileRepository;

    DisciplineController(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    @GetMapping("/discipline")
    public Collection<Discipline> Discipline() {
        return disciplineRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/discipline/{studentproId}/{schoolyear}/{ruleId}/{point}/{punishId}/{since}/{until}")
    public Discipline newDiscipline(@PathVariable long studentproId, @PathVariable String schoolyear,
            @PathVariable long ruleId, @PathVariable long point, @PathVariable long punishId,
            @PathVariable String since, @PathVariable String until) {
        Discipline discipline = new Discipline();
        discipline.setSchoolyear(schoolyear);
        discipline.setSince(since);
        discipline.setUntil(until);
        discipline.setPoint(point);

        StudentProfile studentprofile = studentprofileRepository.findById(studentproId);
        discipline.setStudentprofile(studentprofile);

        Breakrule breakrule = breakruleRepository.findById(ruleId);
        discipline.setBreakrule(breakrule);

        Punish punish = punishRepository.findById(punishId);
        discipline.setPunish(punish);

        return disciplineRepository.save(discipline);
    }
}