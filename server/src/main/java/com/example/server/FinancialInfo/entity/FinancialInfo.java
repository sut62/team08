package com.example.server.FinancialInfo.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

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

    private @NotNull Long finanId;
    private @NotNull Integer academicy;
    @Min(20000) @Max(100000)
    private @NotNull Integer houseincome;
    @Size(min = 2, max = 20)
    private @NotNull String notenough;

    public Long getFinanId() {
        return this.finanId;
    }

    public void setFinanId(Long finanId) {
        this.finanId = finanId;
    }

    public Integer getAcademicy() {
        return this.academicy;
    }

    public void setAcademicy(Integer academicy) {
        this.academicy = academicy;
    }

    public Integer getHouseincome() {
        return this.houseincome;
    }

    public void setHouseincome(Integer houseincome) {
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
    private @NotNull LevelofUse levelofuse;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MoneyFormParent.class)
    @JoinColumn(name = "MoneyFormParent_ID", insertable = true)
    private @NotNull MoneyFormParent moneyformparent;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SpendMoney.class)
    @JoinColumn(name = "SpendMoney_ID", insertable = true)
    private @NotNull SpendMoney spendmoney;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = StudentProfile.class)
    @JoinColumn(name = "StudentProfile_ID", insertable = true)
    private @NotNull StudentProfile studentprofile;

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