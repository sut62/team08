package com.example.server.Discipline.entity;

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
@Table(name = "Breakrule")
public class Breakrule {
    @Id
    @SequenceGenerator(name = "Breakrule_seq", sequenceName = "Breakrule_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Breakrule_seq")
    @Column(name = "Breakrule_ID")

    private @NotNull Long ruleId;
    private @NotNull String ruleType;

    public Long getRuleId() {
        return this.ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleType() {
        return this.ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

}
