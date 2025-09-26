/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cdac.student.dao;

import com.cdac.student.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hcdc
 */
public abstract interface StudentDao extends JpaRepository<StudentDao, Long> {
    public Student findByRollNo(String rollNo);
}
