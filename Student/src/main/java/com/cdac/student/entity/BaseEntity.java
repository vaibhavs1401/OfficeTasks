/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.student.entity;

import jakarta.persistence.MappedSuperclass;

/**
 *
 * @author hcdc
 */

@MappedSuperclass
public class BaseEntity {
    private String email;
    private String password;
    private UserRole userRole;
    
    public enum UserRole {
    ROLE_STUDENT, ROLE_ADMIN
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BaseEntity() {
    }
    
    
    

    public BaseEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    
    
}
