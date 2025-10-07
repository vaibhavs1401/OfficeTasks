package com.cdac.student.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles",
       uniqueConstraints = @UniqueConstraint(name = "uk_roles_name", columnNames = "name"))
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private RoleName name;

    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public enum RoleName { ROLE_ADMIN, ROLE_STUDENT, ROLE_TEACHER }

    public Role() {}
    public Role(RoleName name) { this.name = name; }

    public RoleName getName() { return name; }
    public void setName(RoleName name) { this.name = name; }
}



