package sut.se.g08.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "tb_scholarshipType")
public class ScholarshipType {
    @Id
    @SequenceGenerator(name="scholarshipType_seq",sequenceName="scholarshipType_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="scholarshipType_seq")
    @Column(name="Type_ID")
    private @NonNull Long typeId;
    private @NonNull String scholarshipType;
    private @NonNull Long typescholarship;
}
