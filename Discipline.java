package com.cpe.server.entity;

import lombok.*;
import javax.persistence.*;

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
    @SequenceGenerator(name="Discipline_seq",sequenceName="Discipline_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Discipline_seq")
    @Column(name="Discipline_ID")

    private @NonNull Long disciplineId;
    private @NonNull Long schoolyear;
    private @NonNull Long point;
    private @NonNull String since;
    private @NonNull String until;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Breakrule.class)
    @JoinColumn(name = "Breakrule_ID", insertable = true)
    private Breakrule breakrule;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Punish.class)
    @JoinColumn(name = "Punish_ID", insertable = true)
    private Punish punish;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = StudentProfile.class)
    @JoinColumn(name = "StudentProfile_ID", insertable = true)
    private StudentProfile studentprofile;

	public void setUntil(String until2) {
	}

	public void setSince(String since2) {
	}

	public void setSchoolyear(long schoolyear2) {
	}

	public void setStudentprofile(StudentProfile studentprofile2) {
	}

	public void setBreakrule(Breakrule breakrule2) {
	}

	public void setPunish(Punish punish2) {
	}
}