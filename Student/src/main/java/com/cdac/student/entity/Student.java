package com.cdac.student.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students",
       indexes = {
         @Index(name = "idx_students_rollno", columnList = "roll_no")
       },
       uniqueConstraints = {
         @UniqueConstraint(name = "uk_students_rollno", columnNames = "roll_no")
       })
public class Student extends BaseEntity {

    @Column(nullable = false, length = 120)
    private String name;

    @Column(name = "standard", nullable = false)
    private int standard;          // renamed from 'std' for clarity

    @Column(nullable = false)
    private int age;

    @Column(name = "roll_no", nullable = false, length = 40)
    private String rollNo;

    // One student ↔ one user account (optional; keep if students log in)
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id",
                nullable = false,
                unique = true,
                foreignKey = @ForeignKey(name = "fk_students_user"))
    private UserAccount account;

    public Student() {}

    public Student(String name, int standard, int age, String rollNo, UserAccount account) {
        this.name = name;
        this.standard = standard;
        this.age = age;
        this.rollNo = rollNo;
        this.account = account;
    }

    // getters/setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getStandard() { return standard; }
    public void setStandard(int standard) { this.standard = standard; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }

    public UserAccount getAccount() { return account; }
    public void setAccount(UserAccount account) { this.account = account; }
}


