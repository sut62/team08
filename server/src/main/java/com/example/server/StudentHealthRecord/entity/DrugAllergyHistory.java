package com.example.server.StudentHealthRecord.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="DrugAllergyHistory")
public class DrugAllergyHistory {

    @Id
    @SequenceGenerator(name="DrugAllergyHistory_seq",sequenceName="DrugAllergyHistory_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DrugAllergyHistory_seq")
    @Column(name = "DrugAllergyHistory_ID", unique = true, nullable = false)
    private @NotNull Long drugallergyhistoryid;
    @Pattern(regexp = "^[A-Za-z0-9ก-์\\s]+$")
    private @NotNull String drugallergyhistory;

    public Long getDrugallergyhistoryid() {
        return this.drugallergyhistoryid;
    }

    public void setDrugallergyhistoryid(Long drugallergyhistoryid) {
        this.drugallergyhistoryid = drugallergyhistoryid;
    }


    public String getDrugallergyhistory() {
        return this.drugallergyhistory;
    }

    public void setDrugallergyhistory(String drugallergyhistory) {
        this.drugallergyhistory = drugallergyhistory;
    }
    
	
}