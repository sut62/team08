package com.example.server.Graduates.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.server.Activities.entity.SchoolYear;
import com.example.server.studentprofile.entity.StudentProfile;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Graduates")
public class Graduates {

    @Id
    @SequenceGenerator(name = "Graduates_seq", sequenceName = "Graduates_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Graduates_seq")
    @Column(name = "Graduates_ID", unique = true)

    private @NotNull Long graduaId;
    private @NotNull Date graduadate;
    @Size(min = 5, max = 20)
    @Pattern(regexp = "^[0-9A-Za-zก-์\\s]+$")
    private @NotNull String honor;
    @Pattern(regexp = "^[A-Za-zก-์\\s]+$")
    @Size(min = 5, max = 20)
    private @NotNull String graduation;

    public Long getGraduaId() {
        return this.graduaId;
    }

    public void setGraduaId(Long graduaId) {
        this.graduaId = graduaId;
    }

    public Date getGraduadate() {
        return this.graduadate;
    }

    public void setGraduadate(Date graduadate) {
        this.graduadate = graduadate;
    }

    public String getHonor() {
        return this.honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getGraduation() {
        return this.graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = EducationLevel.class)
    @JoinColumn(name = "EducationLevel_ID", insertable = true)
    private @NotNull EducationLevel educationlevel;

    public EducationLevel getEducationlevel() {
        return this.educationlevel;
    }

    public void setEducationlevel(EducationLevel educationlevel) {
        this.educationlevel = educationlevel;
    }
   
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SchoolYear.class)
    @JoinColumn(name = "SchoolYear_ID", insertable = true)
    private @NotNull SchoolYear schoolyear;

    public SchoolYear getSchoolyear() {
        return this.schoolyear;
    }

    public void setSchoolyear(SchoolYear schoolyear) {
        this.schoolyear = schoolyear;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = StudentProfile.class)
    @JoinColumn(name = "StudentProfile_ID", insertable = true)
    private @NotNull StudentProfile studentprofile;

    public StudentProfile getStudentprofile() {
        return this.studentprofile;
    }

    public void setStudentprofile(StudentProfile studentprofile) {
        this.studentprofile = studentprofile;
    }
    

}
