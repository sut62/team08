package com.example.server.studentprofile.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Major")
public class Major {

    @Id
    @SequenceGenerator(name="Major_seq",sequenceName="Major_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Major_seq")
    @Column(name = "Major_ID", unique = true, nullable = true)

    private @NonNull Long majorId;
    private @NonNull String major;

	public Long getMajorId() {
		return this.majorId;
	}

	public void setMajorId(Long majorId) {
		this.majorId = majorId;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

}