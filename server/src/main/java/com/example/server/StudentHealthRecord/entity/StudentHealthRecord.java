package com.example.server.StudentHealthRecord.entity;

import lombok.*;
import javax.persistence.*;
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

    private @NonNull Long StudentHealthRecordid;
    private @NonNull Integer height;
    private @NonNull Integer weight;

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

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Major.class)
    @JoinColumn(name = "MAJOR_ID")
    private Major major;

    public Major getMajor() {
        return this.major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = StudentProfile.class)
    @JoinColumn(name = "StudentProfile_ID")
    private StudentProfile studentprofile;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DrugAllergyHistory.class)
    @JoinColumn(name = "DrugAllergyHistory_ID")
    private DrugAllergyHistory drugallergyhistory;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CongenitalDisease.class)
    @JoinColumn(name = "CongenitalDisease_ID")
    private CongenitalDisease congenitaldisease;

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