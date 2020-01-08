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
@Table(name = "FinancialInfo")
public class FinancialInfo {
    @Id
    @SequenceGenerator(name="FinancialInfo_seq",sequenceName="FinancialInfo_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FinancialInfo_seq")
    @Column(name="FinancialInfo_ID")

    private @NonNull Long finanId;
    private @NonNull Long academicy;
    private @NonNull Long houseincome;
    private @NonNull String notenough;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = LevelofUse.class)
    @JoinColumn(name = "LevelofUse_ID", insertable = true)
    private LevelofUse levelofuse;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MoneyFormParent.class)
    @JoinColumn(name = "MoneyFormParent_ID", insertable = true)
    private MoneyFormParent moneyformparent;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SpendMoney.class)
    @JoinColumn(name = "SpendMoney_ID", insertable = true)
    private SpendMoney spendmoney;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = StudentProfile.class)
    @JoinColumn(name = "StudentProfile_ID", insertable = true)
    private StudentProfile studentprofile;
}