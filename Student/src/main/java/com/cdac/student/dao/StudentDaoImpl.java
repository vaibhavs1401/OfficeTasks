package com.cdac.student.dao;

import com.cdac.student.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Vaibhav
 */
@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(em.find(Student.class, id));
    }

    @Override
    public Optional<Student> findByRollNo(String rollNo) {
        var list = em.createQuery(
                "SELECT s FROM Student s WHERE s.rollNo = :rollNo", Student.class)
            .setParameter("rollNo", rollNo)
            .getResultList();
        return list.stream().findFirst();
    }

    @Override
    public Optional<Student> findByUserId(Long userId) {
        var list = em.createQuery(
                "SELECT s FROM Student s WHERE s.account.id = :uid", Student.class)
            .setParameter("uid", userId)
            .getResultList();
        return list.stream().findFirst();
    }

    
    @Override
    public List<Student> findAllOrdered() {
        return em.createQuery(
                "SELECT s FROM Student s ORDER BY s.standard ASC, s.name ASC", Student.class)
            .getResultList();
    }

    @Override
    public List<Student> findByStandardOrdered(int std) {
        return em.createQuery(
                "SELECT s FROM Student s WHERE s.standard = :std ORDER BY s.name ASC", Student.class)
            .setParameter("std", std)
            .getResultList();
    }

    @Override
    public Student save(Student student) {
        if (student.getId() == null) {
            em.persist(student);
            return student;
        }
        return em.merge(student);
    }

    @Override
    public void delete(Student student) {
        var managed = em.contains(student) ? student : em.merge(student);
        em.remove(managed);
    }

    
    @Override
    public List<Student> findByName(String name) {
        return em.createQuery("SELECT s FROM Student s WHERE s.name = :name ORDEER BY s.name ASC", Student.class).
                setParameter("name", name)
                .getResultList();
    }
    
    
    
    
}
