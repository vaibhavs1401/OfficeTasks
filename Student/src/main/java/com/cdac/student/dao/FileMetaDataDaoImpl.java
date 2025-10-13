/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.student.dao;

import com.cdac.student.entity.FileMetaData;
import com.cdac.student.entity.UserAccount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hcdc
 */
@Repository
@Transactional
public class FileMetaDataDaoImpl implements FileMetaDataDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(FileMetaData file) {
        em.persist(file);
    }

    @Override
    public Optional<FileMetaData> findById(Long id) {
        return Optional.ofNullable(em.find(FileMetaData.class, id));
    }

    @Override
    public List<FileMetaData> findByStatus(FileMetaData.FileStatus status) {
        return em.createQuery(
                "SELECT f FROM FileMetaData f WHERE f.status = :status", FileMetaData.class)
            .setParameter("status", status)
            .getResultList();
    }

    @Override
    public List<FileMetaData> findByUserIdAndStatus(Long userId, FileMetaData.FileStatus status) {
        return em.createQuery(
                "SELECT f FROM FileMetaData f WHERE f.user.id = :userId AND f.status = :status", FileMetaData.class)
            .setParameter("userId", userId)
            .setParameter("status", status)
            .getResultList();
    }

    
    @Override
    public void updateStatus(Long id, FileMetaData.FileStatus status, LocalDateTime approvedAt) {
        FileMetaData file = em.find(FileMetaData.class, id);
        if (file != null) {
            file.setStatus(status);
            file.setApprovedAt(approvedAt);
            em.merge(file);
        }
    } 
    
    
    
    
        @Override
    public List<FileMetaData> findByStatusPaged(FileMetaData.FileStatus status, int pageNumber, int pageSize) {
        return em.createQuery("SELECT f FROM FileMetaData f WHERE f.status = :status ORDER BY f.uploadedAt DESC", FileMetaData.class)
            .setParameter("status", status)
            .setFirstResult(pageNumber * pageSize)
            .setMaxResults(pageSize)
            .getResultList();
    }

    @Override
    public List<FileMetaData> findAllPaged(int pageNumber, int pageSize) {
        return em.createQuery("SELECT f FROM FileMetaData f ORDER BY f.uploadedAt DESC", FileMetaData.class)
            .setFirstResult(pageNumber * pageSize)
            .setMaxResults(pageSize)
            .getResultList();
    }

    @Override
    public long countByStatus(FileMetaData.FileStatus status) {
        Long count = em.createQuery("SELECT COUNT(f) FROM FileMetaData f WHERE f.status = :status", Long.class)
            .setParameter("status", status)
            .getSingleResult();
        return count != null ? count : 0L;
    }

    @Override
    public long countAll() {
        Long count = em.createQuery("SELECT COUNT(f) FROM FileMetaData f", Long.class)
            .getSingleResult();
        return count != null ? count : 0L;
    }

    @Override
    public List<FileMetaData> findByUser(UserAccount user) {
        return em.createQuery("SELECT f FROM FileMetaData f WHERE f.user = :user ORDER BY f.uploadedAt DESC", FileMetaData.class)
             .setParameter("user", user)
             .getResultList();
    }

    
}
