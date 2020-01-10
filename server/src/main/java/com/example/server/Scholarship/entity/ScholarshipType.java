package com.example.server.Scholarship.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "ScholarshipType")
public class ScholarshipType {
    @Id
    @SequenceGenerator(name = "ScholarshipType_seq", sequenceName = "ScholarshipType_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ScholarshipType_seq")
    @Column(name = "ScholarshipType_ID")
    private @NonNull Long ScholarshipTypeid;
    private @NonNull String goodEducation;
    private @NonNull String educationalLoans;

    public Long getScholarshipTypeid() {
        return this.ScholarshipTypeid;
    }

    public void setScholarshipTypeid(Long ScholarshipTypeid) {
        this.ScholarshipTypeid = ScholarshipTypeid;
    }

    public String getGoodEducation() {
        return this.goodEducation;
    }

    public void setGoodEducation(String goodEducation) {
        this.goodEducation = goodEducation;
    }

    public String getEducationalLoans() {
        return this.educationalLoans;
    }

    public void setEducationalLoans(String educationalLoans) {
        this.educationalLoans = educationalLoans;
    }

}
