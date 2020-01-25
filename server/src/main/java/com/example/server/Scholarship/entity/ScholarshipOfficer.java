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
@Table(name = "ScholarshipOfficer")
public class ScholarshipOfficer {
    @Id
    @SequenceGenerator(name = "ScholarshipOfficer_seq", sequenceName = "ScholarshipOfficer_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ScholarshipOfficer_seq")
    @Column(name = "ScholarshipOfficer_ID")
    private @NotNull Long officerid;
    private @NotNull String officername;

    public Long getOfficerid() {
        return this.officerid;
    }

    public void setOfficerid(Long officerid) {
        this.officerid = officerid;
    }

    public String getOfficername() {
        return this.officername;
    }

    public void setOfficername(String officername) {
        this.officername = officername;
    }

}
