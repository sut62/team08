package com.example.server.GraduateEmployment.entity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.server.studentprofile.entity.StudentProfile;
import com.example.server.Graduates.entity.EducationLevel;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "GraduateEmployment")
public class GraduateEmployment {

    @Id
    @SequenceGenerator(name = "GraduateEmployment_seq", sequenceName = "GraduateEmployment_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GraduateEmployment_seq")
    @Column(name = "GraduateEmployment_ID", unique = true)

    private @NotNull Long gradempId;
    @Size(min = 5, max = 30)
    private @NotNull String workplace;
    private @NotNull String jopposition;
    @Email(message = "Email pattern should be valid")
    private @NotNull String email;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    
    

    public Long getGradempId() {
        return this.gradempId;
    }

    public void setGradempIdId(Long gradempId) {
        this.gradempId = gradempId;
    }

    public String getWorkplace() {
        return this.workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getJopposition() {
        return this.jopposition;
    }

    public void setJopposition(String jopposition) {
        this.jopposition = jopposition;
    }

    

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = WorkStatus.class)
    @JoinColumn(name = "WorkStatus_ID", insertable = true)
    private @NotNull WorkStatus workstatus;

   @ManyToOne(fetch = FetchType.EAGER, targetEntity = StudentProfile.class)
    @JoinColumn(name = "StudentProfile_ID", insertable = true)
    private @NotNull StudentProfile studentprofile;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = EducationLevel.class)
    @JoinColumn(name = "EducationLevel_ID", insertable = true)
    private @NotNull EducationLevel educationlevel;

    public WorkStatus getWorkStatus() {
        return this.workstatus;
    }

    public void setWorkStatus(WorkStatus workstatus) {
        this.workstatus = workstatus;
    }

    
     public StudentProfile getStudentprofile() {
        return this.studentprofile;
    }

    public void setStudentprofile(StudentProfile studentprofile) {
        this.studentprofile = studentprofile;
    }

    public EducationLevel getEducationLevel() {
        return this.educationlevel;
    }

    public void setEducationLevel(EducationLevel educationlevel) {
        this.educationlevel = educationlevel;
    }

}