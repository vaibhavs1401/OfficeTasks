package com.cdac.student.dao;

import com.cdac.student.entity.Student;

import java.util.List;
import java.util.Optional;



public interface StudentDao {

    Optional<Student> findById(Long id);

    Optional<Student> findByRollNo(String rollNo);

    /** Find by the linked UserAccount id (student.account.id)
     * @param userId
     * @return  */
    Optional<Student> findByUserId(Long userId);

    /** Listings
     * @return  */
    List<Student> findAllOrdered();                // ORDER BY standard, name
    List<Student> findByStandardOrdered(int std);  // ORDER BY name

    Student save(Student student);

    void delete(Student student);
}
