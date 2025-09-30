// src/main/java/com/cdac/student/dao/StudentDao.java
package com.cdac.student.dao;

import com.cdac.student.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends JpaRepository<Student, Long>, StudentDaoCustom {
    Student findByRollNo(String rollNo);
    Student findByEmail(String email);
    boolean existsByEmail(String email);
    List<Student> findByStd(int std);
    public void deleteByRollNo(String rollNo);
}
