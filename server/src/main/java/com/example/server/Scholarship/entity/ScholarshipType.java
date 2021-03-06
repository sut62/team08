package com.example.server.Scholarship.entity;

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
@Table(name = "ScholarshipType")
public class ScholarshipType {
    @Id
    @SequenceGenerator(name = "ScholarshipType_seq", sequenceName = "ScholarshipType_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ScholarshipType_seq")
    @Column(name = "ScholarshipType_ID")
    private @NotNull Long ScholarshipTypeid;
    private @NotNull String goodEducation;

    public Long getScholarshipTypeid() {
        return this.ScholarshipTypeid;
    }

    public void setScholarshipTypeid(Long ScholarshipTypeid) {
        this.ScholarshipTypeid = ScholarshipTypeid;
    }

    public String getGoodEducation() {
        return this.goodEducation;
    }

    public void setGoodEducation(String goodEducation) {
        this.goodEducation = goodEducation;
    }

}
