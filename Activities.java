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
    private @NonNull int year;
    private @NonNull String institution;
    private @NonNull String tel;
    
}
