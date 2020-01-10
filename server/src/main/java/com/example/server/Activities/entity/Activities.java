package com.example.server.activities.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.example.server.studentprofile.entity.StudentProfile;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Activities")
public class Activities {

    @Id
    @SequenceGenerator(name = "Activities_seq", sequenceName = "Activities_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Activities_seq")
    @Column(name = "Activities_ID", unique = true, nullable = true)

    private @NotNull long actid;
    private @NotNull Date date;
    private @NotNull String actname;
    private @NotNull String tel;

    public long getActid() {
        return this.actid;
    }

    public void setActid(long actid) {
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

    public void setSchoolyear(SchoolYear schoolyear) {
        this.schoolyear = schoolyear;
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
