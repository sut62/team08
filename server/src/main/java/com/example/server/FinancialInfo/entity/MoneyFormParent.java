package com.example.server.FinancialInfo.entity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "MoneyFormParent")
public class MoneyFormParent {
    @Id
    @SequenceGenerator(name = "MoneyFormParent_seq", sequenceName = "MoneyFormParent_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MoneyFormParent_seq")
    @Column(name = "MoneyFormParent_ID")

    private @NotNull Long moneyparentId;
    private @NotNull String parent;

    public Long getMoneyparentId() {
        return this.moneyparentId;
    }

    public void setMoneyparentId(Long moneyparentId) {
        this.moneyparentId = moneyparentId;
    }

    public String getParent() {
        return this.parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

}