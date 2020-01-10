package com.example.server.activities.entity;

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
    @Column(name = "Institution_ID", unique = true, nullable = true)

    private @NotNull long insid;
    private @NotNull String insname;

    public long getInsid() {
        return this.insid;
    }

    public void setInsid(long insid) {
        this.insid = insid;
    }

    public String getInsname() {
        return this.insname;
    }

    public void setInsname(String insname) {
        this.insname = insname;
    }

}
