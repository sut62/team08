package com.example.server.User.entity;

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
@Table(name = "UserType")
public class UserType {
    @Id
    @SequenceGenerator(name = "UserType_seq", sequenceName = "UserType_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserType_seq")
    @Column(name = "UserType_ID")

    private @NotNull Long userTypeId;
    private @NotNull String userType;

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getUserTypeId() {
        return this.userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }
}
