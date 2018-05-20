package com.kokokozhina.model;

import javax.persistence.*;


@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;



    @Transient
    private String confirmPassword;

//    @ManyToMany
//    @JoinTable(name = "User_roles", joinColumns = @JoinColumn(name = "User_id"),
//            inverseJoinColumns = @JoinColumn(name = "Role_id"))
    @Column(name = "role")
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    //add equals
}
