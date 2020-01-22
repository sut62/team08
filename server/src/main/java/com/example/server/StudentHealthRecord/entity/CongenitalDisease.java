package com.example.server.StudentHealthRecord.entity;
import lombok.*;
import javax.persistence.*;

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
    private @NonNull Long congenitaldiseaseid;
    private @NonNull String congenitaldisease;

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