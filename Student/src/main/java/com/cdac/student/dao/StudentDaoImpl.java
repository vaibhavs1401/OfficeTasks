/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.student.dao;

import com.cdac.student.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 *
 * @author hcdc
 */
public abstract class StudentDaoImpl implements StudentDao{
    
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Student findByEmail(String email){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);
        Predicate emailPredicate = cb.equal(root.get("email"), email);
        cq.where(emailPredicate);
        try {
        return entityManager.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
        return null;
    }
}

    
    
    
    
}
