/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.student.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author hcdc
 */

@Entity
@Table(name = "students")
public class Student extends BaseEntity{
    

    private String name;
    private int std;
    private int age;
    private String rollNo;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStd() {
        return std;
    }

    public void setStd(int std) {
        this.std = std;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public Student(String name, int std, int age, String rollNo, String email, String password) {
        super(email, password);
        this.name = name;
        this.std = std;
        this.age = age;
        this.rollNo = rollNo;
    }

    public Student() {
        
    }
    
    
    
    
}
