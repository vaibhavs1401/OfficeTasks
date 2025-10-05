package com.cdac.student.service;

import com.cdac.student.entity.Student;
import java.util.List;

public interface AdminService {

    Student getStudentByRollNo(String rollNo);

    List<Student> getAllStudents();

    List<Student> getStudentClassWise(int standard);

    List<Student> findStudentsByStd(int standard);

    Student addStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudentByRollNo(String rollNo);
}
