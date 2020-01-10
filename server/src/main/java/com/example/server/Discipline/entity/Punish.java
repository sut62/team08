package com.example.server.Discipline.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Punish")
public class Punish {
    @Id
    @SequenceGenerator(name = "Punish_seq", sequenceName = "Punish_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Punish_seq")
    @Column(name = "Punish_ID")

    private @NonNull Long punishId;
    private @NonNull String punishType;

    public Long getPunishId() {
        return this.punishId;
    }

    public void setPunishId(Long punishId) {
        this.punishId = punishId;
    }

    public String getPunishType() {
        return this.punishType;
    }

    public void setPunishType(String punishType) {
        this.punishType = punishType;
    }

}