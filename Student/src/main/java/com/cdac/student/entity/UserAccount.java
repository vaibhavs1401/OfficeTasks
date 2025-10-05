package com.cdac.student.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users",
       indexes = {
         @Index(name = "idx_users_email", columnList = "email")
       },
       uniqueConstraints = {
         @UniqueConstraint(name = "uk_users_email", columnNames = "email")
       })
public class UserAccount extends BaseEntity implements UserDetails {

    @Column(nullable = false, length = 160)
    private String email;

    @Column(nullable = false, length = 100) // BCrypt ~60 chars; allow future algorithms
    private String password;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean accountNonExpired = true;

    @Column(nullable = false)
    private boolean accountNonLocked = true;

    @Column(nullable = false)
    private boolean credentialsNonExpired = true;

    // Many-to-many roles
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_roles_user")),
        inverseJoinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_user_roles_role")),
        uniqueConstraints = @UniqueConstraint(name = "uk_user_roles_user_role", columnNames = {"user_id","role_id"})
    )
    private Set<Role> roles = new HashSet<>();

    // ----- domain links (optional) -----
    // If each student has exactly one login:
    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private Student student;

    public UserAccount() {}
    public UserAccount(String email, String password) {
        this.email = email;
        this.password = password; // store already-encoded value
    }

    // ---- getters/setters ----
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public void addRole(Role role) { this.roles.add(role); }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    // ---- UserDetails ----
    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
            .map(r -> (GrantedAuthority) () -> r.getName().name())
            .collect(Collectors.toUnmodifiableSet());
    }

    @Override public String getUsername() { return email; }
    @Override public boolean isAccountNonExpired() { return accountNonExpired; }
    @Override public boolean isAccountNonLocked() { return accountNonLocked; }
    @Override public boolean isCredentialsNonExpired() { return credentialsNonExpired; }
    @Override public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public void setAccountNonExpired(boolean v) { this.accountNonExpired = v; }
    public void setAccountNonLocked(boolean v) { this.accountNonLocked = v; }
    public void setCredentialsNonExpired(boolean v) { this.credentialsNonExpired = v; }
}
