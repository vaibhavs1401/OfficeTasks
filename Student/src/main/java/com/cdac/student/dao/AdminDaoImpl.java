package com.cdac.student.dao;

import com.cdac.student.entity.Admin;
import com.cdac.student.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class AdminDaoImpl implements AdminDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Student getStudentByRollNo(String rollNo) {
        TypedQuery<Student> q = em.createQuery(
                "SELECT s FROM Student s WHERE s.rollNo = :rollNo", Student.class);
        q.setParameter("rollNo", rollNo);
        List<Student> res = q.getResultList();
        return res.isEmpty() ? null : res.get(0);
    }

    @Override
    public List<Student> getAllStudents() {
        return em.createQuery(
                "SELECT s FROM Student s ORDER BY s.standard ASC, s.name ASC",
                Student.class).getResultList();
    }

    @Override
    public List<Student> getStudentsByStandard(int standard) {
        TypedQuery<Student> q = em.createQuery(
                "SELECT s FROM Student s WHERE s.standard = :std ORDER BY s.name ASC",
                Student.class);
        q.setParameter("std", standard);
        return q.getResultList();
    }

    @Override
    public Student saveOrUpdate(Student student) {
        if (student.getId() == null) {
            em.persist(student);
            return student;
        } else {
            return em.merge(student);
        }
    }

    @Override
    public void deleteByRollNo(String rollNo) {
        Student s = getStudentByRollNo(rollNo);
        if (s != null) {
            em.remove(em.contains(s) ? s : em.merge(s));
        }
    }

    @Override
    public void saveOrUpdate(Admin admin) {
        if (admin.getId() == null) {
            em.persist(admin);
        } else {
            em.merge(admin);
        }
    }
}
