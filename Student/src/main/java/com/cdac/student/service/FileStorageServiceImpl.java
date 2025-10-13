/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.student.service;

import com.cdac.student.dao.FileMetaDataDao;
import com.cdac.student.entity.FileMetaData;
import com.cdac.student.entity.Student;
import com.cdac.student.entity.UserAccount;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hcdc
 */
@Service
@Transactional
public class FileStorageServiceImpl implements FileStorageService {

    private final FileMetaDataDao fileMetaDataDao;

    private String uploadDir = "D:\\SpringPractice\\Docs";

    public FileStorageServiceImpl(FileMetaDataDao fileMetaDataDao) {
        this.fileMetaDataDao = fileMetaDataDao;
    }

    @Override
    public void storeFile(MultipartFile file, Student student) throws IOException {
        // Generate stored filename
        String storedFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // Save to disk
        Path destination = Paths.get(uploadDir).resolve(storedFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        // Save metadata to DB
        FileMetaData meta = new FileMetaData();
        meta.setStoredFilename(storedFileName);
        meta.setOriginalFileName(file.getOriginalFilename());
        meta.setContentType(file.getContentType());
        meta.setSize(file.getSize());
        meta.setStatus(FileMetaData.FileStatus.PENDING);
        meta.setUploadedAt(LocalDateTime.now());
        meta.setUser(student.getAccount());
        fileMetaDataDao.save(meta);
    }

    @Override
        public List<FileMetaData> findFilesByUser(UserAccount user) {
            return fileMetaDataDao.findByUser(user);
        }
}

