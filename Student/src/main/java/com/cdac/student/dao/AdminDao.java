package com.cdac.student.dao;

import com.cdac.student.entity.Admin;
import com.cdac.student.entity.Student;
import java.util.List;

public interface AdminDao {

    Student getStudentByRollNo(String rollNo);

    List<Student> getAllStudents();

    List<Student> getStudentsByStandard(int standard);

    Student saveOrUpdate(Student student);

    void deleteByRollNo(String rollNo);

    public void saveOrUpdate(Admin admin);
}
