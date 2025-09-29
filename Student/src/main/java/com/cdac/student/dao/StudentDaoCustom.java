package com.cdac.student.dao;

import com.cdac.student.entity.Student;
import java.util.List;

public interface StudentDaoCustom {
    List<Student> findByClassUsingCriteria(int std);
}
