package com.example.server.activities.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Activities")
public class Activities {

    @Id
    @SequenceGenerator(name="Activities_seq",sequenceName="Activities_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Activities_seq")
    @Column(name = "Activities_ID", unique = true, nullable = true)

    private @NonNull Long actid;
    private @NonNull Date date;
    private @NonNull String actname;
    private @NonNull String tel;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SchoolYear.class)
    @JoinColumn(name = "SchoolYear_ID", insertable = true)
    private SchoolYear schoolyear;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Institution.class)
    @JoinColumn(name = "Institution_ID", insertable = true)
    private Institution institution;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name="StudentProfile_ID")
    private StudentProfile studentprofile;

}
