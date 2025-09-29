package com.cdac.student.dao;

import com.cdac.student.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class StudentDaoImpl implements StudentDaoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findByClassUsingCriteria(int std) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);

        cq.select(root).where(cb.equal(root.get("std"), std));
        return entityManager.createQuery(cq).getResultList();
    }
}
