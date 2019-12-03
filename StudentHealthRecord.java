package sut.se.g08.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "StudentHealthRecord")
public class VinaiType {
    @Id
    @SequenceGenerator(name="studenthealthrecord_seq",sequenceName="studenthealthrecord_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="studenthealthrecord_seq")
    @Column(name="studenthealthrecord_ID")
    private @NonNull Long StudentId;
    private @NonNull String  name;
    private @NonNull String healthinfo;
}
