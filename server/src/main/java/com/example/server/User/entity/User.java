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
@Table(name = "User")
public class User {
    @Id
    @SequenceGenerator(name = "User_Seq", sequenceName = "User_Seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_Seq")
    @Column(name = "User_ID")

    private @NotNull Long userId;
    private @NotNull String username;
    private @NotNull String password;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = UserType.class)
    @JoinColumn(name = "UserType_Id", insertable = true)
    private @NotNull UserType userType;

    public UserType getUserType() {
        return this.userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
