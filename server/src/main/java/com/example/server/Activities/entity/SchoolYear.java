package com.example.server.activities.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="SchoolYear")
public class SchoolYear {

    @Id
    @SequenceGenerator(name="SchoolYear_seq",sequenceName="SchoolYear_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SchoolYear_seq")
    @Column(name = "SchoolYear_ID", unique = true)

    private @NotNull Long yearid;
    private @NotNull Integer scyear;

    public Long getYearid() {
        return this.yearid;
    }

    public void setYearid(Long yearid) {
        this.yearid = yearid;
    }

    public Integer getScyear() {
        return this.scyear;
    }

    public void setScyear(Integer scyear) {
        this.scyear = scyear;
    }

}
