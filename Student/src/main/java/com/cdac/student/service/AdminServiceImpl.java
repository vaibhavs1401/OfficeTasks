package com.cdac.student.service;

import com.cdac.student.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Thin orchestration layer for admin use-cases.
 * Delegates to StudentService for persistence.
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final StudentService studentService;

    public AdminServiceImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public Student getStudentByRollNo(String rollNo) {
        return studentService.getStudentByRollNo(rollNo);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Override
    public List<Student> getStudentClassWise(int standard) {
        return studentService.getStudentClassWise(standard);
    }

    @Override
    public List<Student> findStudentsByStd(int standard) {
        return studentService.findStudentsByStd(standard);
    }

    @Override
    public Student addStudent(Student student) {
        return studentService.addStudent(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentService.updateStudent(student);
    }

    @Override
    public void deleteStudentByRollNo(String rollNo) {
        studentService.deleteStudentByRollNo(rollNo);
    }
}
