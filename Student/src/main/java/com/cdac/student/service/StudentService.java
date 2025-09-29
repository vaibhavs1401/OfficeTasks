// src/main/java/com/cdac/student/service/StudentService.java
package com.cdac.student.service;

import com.cdac.student.entity.Student;
import java.util.List;

public interface StudentService {
    String register(Student s);                         
    Student authenticate(String email, String password); 
    Student getStudentByRollNo(String rollNo);
    List<Student> getAllStudents();
    List<Student> getStudentClassWise(int std);
}
