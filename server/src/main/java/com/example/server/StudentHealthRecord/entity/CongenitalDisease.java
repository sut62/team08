package com.example.server.StudentHealthRecord.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="CongenitalDisease")
public class CongenitalDisease {

    @Id
    @SequenceGenerator(name="CongenitalDisease_seq",sequenceName="CongenitalDisease_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CongenitalDisease_seq")
    @Column(name = "CongenitalDisease_ID", unique = true, nullable = false)
    private @NotNull Long congenitaldiseaseid;
    @Size(min = 5, max = 30)
    private @NotNull String congenitaldisease;

    public Long getCongenitaldiseaseid() {
        return this.congenitaldiseaseid;
    }

    public void setCongenitaldiseaseid(Long congenitaldiseaseid) {
        this.congenitaldiseaseid = congenitaldiseaseid;
    }


    public String getCongenitaldisease() {
        return this.congenitaldisease;
    }

    public void setCongenitaldisease(String congenitaldisease) {
        this.congenitaldisease = congenitaldisease;
    }



}