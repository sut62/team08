package com.example.server.Discipline.entity;

import lombok.*;
import javax.persistence.*;

import com.example.server.studentprofile.entity.StudentProfile;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Discipline")
public class Discipline {
    @Id
    @SequenceGenerator(name = "Discipline_seq", sequenceName = "Discipline_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Discipline_seq")
    @Column(name = "Discipline_ID")

    private @NonNull Long disciplineId;
    private @NonNull Long schoolyear;
    private @NonNull Long point;
    private @NonNull String since;
    private @NonNull String until;

    public Long getDisciplineId() {
        return this.disciplineId;
    }

    public void setDisciplineId(Long disciplineId) {
        this.disciplineId = disciplineId;
    }

    public Long getSchoolyear() {
        return this.schoolyear;
    }

    public void setSchoolyear(Long schoolyear) {
        this.schoolyear = schoolyear;
    }

    public Long getPoint() {
        return this.point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public String getSince() {
        return this.since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getUntil() {
        return this.until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Breakrule.class)
    @JoinColumn(name = "Breakrule_ID", insertable = true)
    private Breakrule breakrule;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Punish.class)
    @JoinColumn(name = "Punish_ID", insertable = true)
    private Punish punish;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = StudentProfile.class)
    @JoinColumn(name = "StudentProfile_ID", insertable = true)
    private StudentProfile studentprofile;

    public Breakrule getBreakrule() {
        return this.breakrule;
    }

    public void setBreakrule(Breakrule breakrule) {
        this.breakrule = breakrule;
    }

    public Punish getPunish() {
        return this.punish;
    }

    public void setPunish(Punish punish) {
        this.punish = punish;
    }

    public StudentProfile getStudentprofile() {
        return this.studentprofile;
    }

    public void setStudentprofile(StudentProfile studentprofile) {
        this.studentprofile = studentprofile;
    }

}