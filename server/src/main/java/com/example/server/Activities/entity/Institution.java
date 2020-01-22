package com.example.server.Activities.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Institution")
public class Institution {

    @Id
    @SequenceGenerator(name = "Institution_seq", sequenceName = "Institution_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Institution_seq")
    @Column(name = "Institution_ID", unique = true)

    private @NotNull Long insid;
    private @NotNull String insname;

    public Long getInsid() {
        return this.insid;
    }

    public void setInsid(Long insid) {
        this.insid = insid;
    }

    public String getInsname() {
        return this.insname;
    }

    public void setInsname(String insname) {
        this.insname = insname;
    }

}
