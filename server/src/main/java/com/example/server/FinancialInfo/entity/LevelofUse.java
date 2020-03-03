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
@Table(name = "LevelofUse")
public class LevelofUse {
    @Id
    @SequenceGenerator(name = "LevelofUse_seq", sequenceName = "LevelofUse_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LevelofUse_seq")
    @Column(name = "LevelofUse_ID")

    private @NotNull Long levelId;
    private @NotNull String used;

    public Long getLevelId() {
        return this.levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public String getUsed() {
        return this.used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

}
