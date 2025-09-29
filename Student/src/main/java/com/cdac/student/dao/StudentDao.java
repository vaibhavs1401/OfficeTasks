// src/main/java/com/cdac/student/dao/StudentDao.java
package com.cdac.student.dao;

import com.cdac.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student, Long>, StudentDaoCustom {
    Student findByRollNo(String rollNo);
    Student findByEmail(String email);
    boolean existsByEmail(String email);
    List<Student> findByStd(int std);
}
