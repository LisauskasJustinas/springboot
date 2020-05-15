package com.klientai.model;

import javax.persistence.*;
import java.util.Set;
import javax.persistence.Table;
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;

    private String email;




    @Transient
    private String passwordConfirm;
    @Transient
    private String emailConfirm;
    @ManyToMany
    @JoinTable(name="user_roles",joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns =@JoinColumn(name="role_id"))
    private Set<Role> roles;

    public User(int id, String username, String password, String passwordConfirm, Set<Role> roles,String email,String emailConfirm) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
        this.email=email;
        this.emailConfirm=emailConfirm;
    }

    public User() {

    }

    public int getId() {
        return id;
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
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmailConfirm() {
        return emailConfirm;
    }

    public void setEmailConfirm(String emailConfirm) {
        this.emailConfirm = emailConfirm;
    }

}
