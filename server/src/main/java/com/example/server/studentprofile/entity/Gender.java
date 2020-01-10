package com.example.server.studentprofile.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Gender")
public class Gender {

    @Id
    @SequenceGenerator(name="Gender_seq",sequenceName="Gender_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Gender_seq")
    @Column(name = "Gender_ID", unique = true, nullable = true)

    private @NonNull Long genderId;
    private @NonNull String gender;

	public Long getGenderId() {
		return this.genderId;
	}

	public void setGenderId(Long genderId) {
		this.genderId = genderId;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}