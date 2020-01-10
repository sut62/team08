package com.example.server.FinancialInfo.entity;

import lombok.*;
import javax.persistence.*;

import com.example.server.studentprofile.entity.StudentProfile;

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
    @SequenceGenerator(name = "FinancialInfo_seq", sequenceName = "FinancialInfo_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FinancialInfo_seq")
    @Column(name = "FinancialInfo_ID")

    private @NonNull Long finanId;
    private @NonNull Long academicy;
    private @NonNull Long houseincome;
    private @NonNull String notenough;

    public Long getFinanId() {
        return this.finanId;
    }

    public void setFinanId(Long finanId) {
        this.finanId = finanId;
    }

    public Long getAcademicy() {
        return this.academicy;
    }

    public void setAcademicy(Long academicy) {
        this.academicy = academicy;
    }

    public Long getHouseincome() {
        return this.houseincome;
    }

    public void setHouseincome(Long houseincome) {
        this.houseincome = houseincome;
    }

    public String getNotenough() {
        return this.notenough;
    }

    public void setNotenough(String notenough) {
        this.notenough = notenough;
    }

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

    public MoneyFormParent getMoneyformparent() {
        return this.moneyformparent;
    }

    public void setMoneyformparent(MoneyFormParent moneyformparent) {
        this.moneyformparent = moneyformparent;
    }

    public LevelofUse getLevelofuse() {
        return this.levelofuse;
    }

    public void setLevelofuse(LevelofUse levelofuse) {
        this.levelofuse = levelofuse;
    }

    public StudentProfile getStudentprofile() {
        return this.studentprofile;
    }

    public void setStudentprofile(StudentProfile studentprofile) {
        this.studentprofile = studentprofile;
    }

    public SpendMoney getSpendmoney() {
        return this.spendmoney;
    }

    public void setSpendmoney(SpendMoney spendmoney) {
        this.spendmoney = spendmoney;
    }
}