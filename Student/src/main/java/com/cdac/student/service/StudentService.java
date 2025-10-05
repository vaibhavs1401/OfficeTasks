package com.cdac.student.service;

import com.cdac.student.entity.Student;
import java.util.List;

public interface StudentService {

    // ----- student self-service -----
    Student findByUserId(Long userId);

    void updateOwnProfile(Long userId, Student form);

    // ----- shared reads (used by admin and controllers) -----
    Student getStudentByRollNo(String rollNo);

    Student findByRollNo(String rollNo);

    List<Student> getAllStudents();

    List<Student> getStudentClassWise(int standard);

    List<Student> findStudentsByStd(int standard);

    Student addStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudentByRollNo(String rollNo);
}
