package sut.se.g08.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "tb_vinaiType")
public class VinaiType {
    @Id
    @SequenceGenerator(name="vinaiType_seq",sequenceName="vinaiType_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="vinaiType_seq")
    @Column(name="Type_ID")
    private @NonNull Long typeId;
    private @NonNull String vinaiType;
    private @NonNull Long typePoint;
}
