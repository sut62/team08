package com.cpe.backend.entity;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="StudentHealthRecord")
public class StudentHealthRecord {
    @Id
    @SequenceGenerator(name="StudentHealthRecord_seq",sequenceName="StudentHealthRecord_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="StudentHealthRecord_seq")
    @Column(name = "StudentHealthRecord_ID", unique = true, nullable = false)
	
	private @NonNull Long StudentHealthRecordid;
	private @NonNull Integer weight;
    private @NonNull Integer height;
    
    
   
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Major.class)
    @JoinColumn(name = "MAJOR_ID")
    private Major major;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = StudentProfile.class)
    @JoinColumn(name = "StudentProfile_ID")
    private StudentProfile studentprofile;

	
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DrugAllergyHistory.class)
    @JoinColumn(name = "DrugAllergyHistory_ID")
    private DrugAllergyHistory drugallergyhistory;

	
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CongenitalDisease.class)
    @JoinColumn(name = "CongenitalDisease_ID")
    private CongenitalDisease congenitaldisease;
}