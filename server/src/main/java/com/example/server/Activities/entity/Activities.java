package com.example.server.Activities.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import com.example.server.studentprofile.entity.StudentProfile;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Activities")
public class Activities {

    @Id
    @SequenceGenerator(name = "Activities_seq", sequenceName = "Activities_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Activities_seq")
    @Column(name = "Activities_ID", unique = true)

    private @NotNull Long actid;
    private @NotNull Date date;

    @Size(min = 5, max = 20)
    private @NotNull String actname;

    @Pattern(regexp = "\\d{10}")
    private @NotNull String tel;

    public Long getActid() {
        return this.actid;
    }

    public void setActid(Long actid) {
        this.actid = actid;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getActname() {
        return this.actname;
    }

    public void setActname(String actname) {
        this.actname = actname;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SchoolYear.class)
    @JoinColumn(name = "SchoolYear_ID", insertable = true)
    private SchoolYear schoolyear;

    public SchoolYear getSchoolyear() {
        return this.schoolyear;
    }

    public void setSchoolyear(SchoolYear schoolyears) {
        this.schoolyear = schoolyears;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Institution.class)
    @JoinColumn(name = "Institution_ID", insertable = true)
    private Institution institution;

    public Institution getInstitution() {
        return this.institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "StudentProfile_ID")
    private StudentProfile studentprofile;

    public StudentProfile getStudentprofile() {
        return this.studentprofile;
    }

    public void setStudentprofile(StudentProfile studentprofile) {
        this.studentprofile = studentprofile;
    }

}
