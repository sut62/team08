package com.example.server.GraduateEmployment.entity;
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
@Table(name = "WorkStatus")
public class WorkStatus {

    @Id
    @SequenceGenerator(name="WorkStatus_seq",sequenceName="WorkStatus_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="WorkStatus_seq")
    @Column(name = "WorkStatus_ID", unique = true)

    private @NotNull Long workstatusId;
    private @NotNull String status;
    
    public Long getWorkstatusId() {
        return this.workstatusId;
    }

    public void setWorkstatusId(Long workstatusId) {
        this.workstatusId = workstatusId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}