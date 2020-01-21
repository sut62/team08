package com.example.server.studentprofile.entity;

import lombok.*;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "StudentProfile")
public class StudentProfile {

	@Id
	@SequenceGenerator(name = "StudentProfile_seq", sequenceName = "StudentProfile_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StudentProfile_seq")
	@Column(name = "StudentProfile_ID", unique = true)

	private @NotNull Long studentproId;

	@Pattern(regexp = "\\d{13}")
	private @NotNull String idnumber;
	private @NotNull String namethai;
	private @NotNull String nameeng;
	private @NotNull String idcard;
	private @NotNull Date brithday;
	private @NotNull Integer age;
	private @NotNull String blood;

	@Size(min = 10, max = 10)
    private @NotNull String tel;
	private @NotNull String address;

	public Long getStudentproId() {
		return this.studentproId;
	}

	public void setStudentproId(Long studentproId) {
		this.studentproId = studentproId;
	}

	public String getIdnumber() {
		return this.idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getNamethai() {
		return this.namethai;
	}

	public void setNamethai(String namethai) {
		this.namethai = namethai;
	}

	public String getNameeng() {
		return this.nameeng;
	}

	public void setNameeng(String nameeng) {
		this.nameeng = nameeng;
	}

	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Date getBrithday() {
		return this.brithday;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getBlood() {
		return this.blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
	@JoinColumn(name = "Gender_ID", insertable = true)
	private Gender gender;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Major.class)
	@JoinColumn(name = "Major_ID", insertable = true)
	private Major major;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
	@JoinColumn(name = "Status_ID", insertable = true)
	private Status status;

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Major getMajor() {
		return this.major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}