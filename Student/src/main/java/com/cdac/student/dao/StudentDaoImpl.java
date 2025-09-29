package com.cdac.student.dao;

import com.cdac.student.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public abstract class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Student findByRollNo(String rollNo) {
        // Implement as needed or leave for JpaRepository default if exists
        throw new UnsupportedOperationException("Not implemented");
    }

    public Student findByEmail(String email) {
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

    public List<Student> getStudentsByClass(String studentClass) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Student> findByClassUsingCriteria(String studentClass) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);

        Predicate classPredicate = cb.equal(root.get("studentClass"), studentClass);
        cq.where(classPredicate);

        return entityManager.createQuery(cq).getResultList();
    }
}
