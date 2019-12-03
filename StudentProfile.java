package com.cpe.se62.team08;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "StudentProfile")
public class StudentProfile {
    @Id
    @SequenceGenerator(name="StudentProfile_seq",sequenceName="StudentProfile_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="StudentProfile_seq")
    @Column(name="StudentProfile_ID")
    private @NonNull Long profileId;

}