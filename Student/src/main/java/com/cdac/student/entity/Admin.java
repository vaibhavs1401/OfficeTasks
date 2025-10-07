package com.cdac.student.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admins",
       uniqueConstraints = @UniqueConstraint(name = "uk_admins_user", columnNames = "user_id"))
public class Admin extends BaseEntity {

    @Column(length = 120)
    private String displayName;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true,
                foreignKey = @ForeignKey(name = "fk_admins_user"))
    private UserAccount account;

    public Admin() {}
    public Admin(UserAccount account, String displayName) {
        this.account = account;
        this.displayName = displayName;
    }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public UserAccount getAccount() { return account; }
    public void setAccount(UserAccount account) { this.account = account; }
}

