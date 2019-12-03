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
@Table(name = "FinancialInfo")
public class FinancialInfo {
    @Id
    @SequenceGenerator(name="FinancialInfo_seq",sequenceName="FinancialInfo_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FinancialInfo_seq")
    @Column(name="FinancialInfo_ID")

    private @NonNull Long finanId;

}