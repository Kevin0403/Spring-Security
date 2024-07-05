package org.example.signupwithsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name=  "fk_username", nullable = false, unique = true)
    private User user;

    @Transient
    private String username;
    @Transient
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @OneToMany(cascade = CascadeType.ALL)
    @Transient
    private List<Role> roles;


}
