package com.example.server.studentprofile.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Status")
public class Status {

    @Id
    @SequenceGenerator(name="Status_seq",sequenceName="Status_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Status_seq")
    @Column(name = "Status_ID", unique = true, nullable = true)

    private @NonNull long statusId;
    private @NonNull String status;

	public long getStatusId() {
		return this.statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
