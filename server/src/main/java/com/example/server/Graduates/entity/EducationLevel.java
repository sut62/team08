package com.example.server.Graduates.entity;

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
@Table(name = "EducationLevel")
public class EducationLevel {

    @Id
    @SequenceGenerator(name="EducationLevel_seq",sequenceName="EducationLevel_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EducationLevel_seq")
    @Column(name = "EducationLevel_ID", unique = true)

    private @NotNull Long educaId;
    private @NotNull String educalevel;
    
    public Long getEducaId() {
        return this.educaId;
    }

    public void setEducaId(Long educaId) {
        this.educaId = educaId;
    }

    public String getEducalevel() {
        return this.educalevel;
    }

    public void setEducalevel(String educalevel) {
        this.educalevel = educalevel;
    }
}