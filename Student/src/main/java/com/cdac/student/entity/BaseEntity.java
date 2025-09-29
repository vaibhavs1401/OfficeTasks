package com.cdac.student.entity;

import jakarta.persistence.*;

@MappedSuperclass
public class BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @Enumerated(EnumType.ORDINAL)            // tinyint(0/1) ⇄ enum ordinal
    @Column(name = "userRole", nullable = true)
    private UserRole userRole;

    public enum UserRole { ROLE_STUDENT, ROLE_ADMIN }

    public BaseEntity() {}

    public BaseEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UserRole getUserRole() { return userRole; }
    public void setUserRole(UserRole userRole) { this.userRole = userRole; }
}
