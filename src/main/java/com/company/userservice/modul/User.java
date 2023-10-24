package com.company.userservice.modul;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")

@NamedQueries(value = {
        @NamedQuery(name = "findAllUsers", query = "select u from User as u where u.firstName like concat(:v, '%')"),
        @NamedQuery(name = "existByEmail", query = "select case when count(u.userId) > 0 " +
                "then false else true end from User as u where u.email = :e")
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;// user_id

    @Column(name = "firstname")
    private String firstName;
    private String lastName;
    private String email;
    @Column(name = "gender_id")
    private String gender;
    private String phone;
    private String password;
    @Column(name = "birth_date")
    private String birthDate;

    private String username;
    private Boolean enabled;

    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    private Set<Card> cards;

    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
