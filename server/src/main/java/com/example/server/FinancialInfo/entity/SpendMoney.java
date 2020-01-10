package com.example.server.FinancialInfo.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "SpendMoney")
public class SpendMoney {
    @Id
    @SequenceGenerator(name = "SpendMoney_seq", sequenceName = "SpendMoney_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SpendMoney_seq")
    @Column(name = "SpendMoney_ID")

    private @NonNull Long spendId;
    private @NonNull String usemoney;

    public Long getSpendId() {
        return this.spendId;
    }

    public void setSpendId(Long spendId) {
        this.spendId = spendId;
    }

    public String getUsemoney() {
        return this.usemoney;
    }

    public void setUsemoney(String usemoney) {
        this.usemoney = usemoney;
    }

}