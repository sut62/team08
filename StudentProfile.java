package com.example.server.studentprofile.entity;

import lombok.*;
import java.util.Date;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="StudentProfile")
public class StudentProfile {

    @Id
    @SequenceGenerator(name="StudentProfile_seq",sequenceName="StudentProfile_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="StudentProfile_seq")
    @Column(name = "StudentProfile_ID", unique = true, nullable = true)

    private @NonNull Long studentproId;
    private @NonNull String idnumber;
    private @NonNull String namethai;
    private @NonNull String nameeng;
    private @NonNull String idcard;
    private @NonNull Date brithday;
    private @NonNull Integer age;
    private @NonNull String blood;
    private @NonNull String tel;
    private @NonNull String address;
    

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "Gender_ID", insertable = true)
    private Gender gender;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Major.class)
    @JoinColumn(name = "Major_ID", insertable = true)
    private Major major;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
    @JoinColumn(name = "Status_ID", insertable = true)
    private Status status;

	public void setBrithday(Date brithday) {
	}
	public void setstatus(Status statuss) {
	}
	public void setMajor(Major majors) {
	}
	public void setGender(Gender genders) {
	}
}