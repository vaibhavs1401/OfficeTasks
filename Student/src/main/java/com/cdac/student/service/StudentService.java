/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cdac.student.service;

import com.cdac.student.entity.Student;
import java.util.List;

/**
 *
 * @author hcdc
 */
public interface StudentService {

    public String register(Student s);

    public Student getStudentByRollNo(String rollNo);

    public List<Student> getAllStudents();
    
    
}
