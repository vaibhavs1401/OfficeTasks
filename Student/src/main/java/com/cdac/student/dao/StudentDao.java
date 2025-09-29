package com.cdac.student.dao;

import com.cdac.student.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
    Student findByRollNo(String rollNo);

    // Custom method to be implemented in StudentDaoImpl for criteria-based query
    List<Student> findByClassUsingCriteria(String studentClass);
}
