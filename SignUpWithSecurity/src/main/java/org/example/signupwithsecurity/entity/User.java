package org.example.signupwithsecurity.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "AUTH_USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGenerator")
    @TableGenerator(name = "tableGenerator", allocationSize = 1, initialValue = 100)
    private int id;

    private String username;

    public List<Role> getRoles() {
        return roles;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "AUTH_USERS_ROLES",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;

    public int getId() {
        return id;
    }

    public User() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
