/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.student.dao;

import com.cdac.student.entity.FileMetaData;
import com.cdac.student.entity.UserAccount;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author hcdc
 */
public interface FileMetaDataDao {
    void save(FileMetaData file);

    Optional<FileMetaData> findById(Long id);

    List<FileMetaData> findByStatus(FileMetaData.FileStatus status);

    List<FileMetaData> findByUserIdAndStatus(Long userId, FileMetaData.FileStatus status);

    void updateStatus(Long id, FileMetaData.FileStatus status, LocalDateTime approvedAt);
    
    List<FileMetaData> findByStatusPaged(FileMetaData.FileStatus status, int pageNumber, int pageSize);

    List<FileMetaData> findAllPaged(int pageNumber, int pageSize);

    long countByStatus(FileMetaData.FileStatus status);

    long countAll();

    public List<FileMetaData> findByUser(UserAccount user);
    
}

