package com.example.server.Scholarship.entity;

import lombok.*;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.server.studentprofile.entity.StudentProfile;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Scholarship")
public class Scholarship {
    @Id
    @SequenceGenerator(name = "Scholarship_seq", sequenceName = "Scholarship_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Scholarship_seq")
    @Column(name = "Scholarship_ID")

    private @NotNull Long scholarshipid;
    private @NotNull Date paydate;
    
    @Size(min = 3, max = 5)
    private @NotNull String money;

    public String getMoney() {
        return this.money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Long getScholarshipid() {
        return this.scholarshipid;
    }

    public void setScholarshipid(Long scholarshipid) {
        this.scholarshipid = scholarshipid;
    }

    public Date getPaydate() {
        return this.paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ScholarshipType.class)
    @JoinColumn(name = "Scholarshiptype_ID", insertable = true)
    private @NotNull ScholarshipType scholarshipType;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = StudentProfile.class)
    @JoinColumn(name = "Student_ID", insertable = true)
    private @NotNull StudentProfile studentProfile;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ScholarshipOfficer.class)
    @JoinColumn(name = "ScholarshipOfficer_ID", insertable = true)
    private @NotNull ScholarshipOfficer scholarshipOfficer;

    public ScholarshipType getScholarshipType() {
        return this.scholarshipType;
    }

    public void setScholarshipType(ScholarshipType scholarshipType) {
        this.scholarshipType = scholarshipType;
    }

    public ScholarshipOfficer getScholarshipOfficer() {
        return this.scholarshipOfficer;
    }

    public void setScholarshipOfficer(ScholarshipOfficer scholarshipOfficer) {
        this.scholarshipOfficer = scholarshipOfficer;
    }

    public StudentProfile getStudentProfile() {
        return this.studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }
}