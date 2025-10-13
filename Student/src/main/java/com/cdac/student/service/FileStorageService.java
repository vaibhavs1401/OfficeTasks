/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.student.service;

import com.cdac.student.entity.FileMetaData;
import com.cdac.student.entity.Student;
import com.cdac.student.entity.UserAccount;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hcdc
 */
public interface FileStorageService {
    
    void storeFile(MultipartFile file, Student student) throws IOException;

    public List<FileMetaData> findFilesByUser(UserAccount currentUser);
}


