package com.example.server.StudentHealthRecord.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.example.server.studentprofile.entity.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "StudentHealthRecord")
public class StudentHealthRecord {
    @Id
    @SequenceGenerator(name = "StudentHealthRecord_seq", sequenceName = "StudentHealthRecord_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StudentHealthRecord_seq")
    @Column(name = "StudentHealthRecord_ID", unique = true, nullable = false)

    private @NotNull Long StudentHealthRecordid;
    @Min(100) @Max(220)
    private @NotNull Integer height;
    @Min(30) @Max(200)
    private @NotNull Integer weight;

    public Long getStudentHealthRecordid() {
        return this.StudentHealthRecordid;
    }

    public void setStudentHealthRecordid(Long StudentHealthRecordid) {
        this.StudentHealthRecordid = StudentHealthRecordid;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = StudentProfile.class)
    @JoinColumn(name = "StudentProfile_ID")
    private @NotNull StudentProfile studentprofile;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DrugAllergyHistory.class)
    @JoinColumn(name = "DrugAllergyHistory_ID")
    private @NotNull DrugAllergyHistory drugallergyhistory;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CongenitalDisease.class)
    @JoinColumn(name = "CongenitalDisease_ID")
    private @NotNull CongenitalDisease congenitaldisease;

    public StudentProfile getStudentprofile() {
        return this.studentprofile;
    }

    public void setStudentprofile(StudentProfile studentprofile) {
        this.studentprofile = studentprofile;
    }

    public DrugAllergyHistory getDrugallergyhistory() {
        return this.drugallergyhistory;
    }

    public void setDrugallergyhistory(DrugAllergyHistory drugallergyhistory) {
        this.drugallergyhistory = drugallergyhistory;
    }

    public CongenitalDisease getCongenitaldisease() {
        return this.congenitaldisease;
    }

    public void setCongenitaldisease(CongenitalDisease congenitaldisease) {
        this.congenitaldisease = congenitaldisease;
    }

}