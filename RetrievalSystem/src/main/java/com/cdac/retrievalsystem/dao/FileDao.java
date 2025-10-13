/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.retrievalsystem.dao;

import com.cdac.retrievalsystem.model.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hcdc
 */


public interface FileDao extends JpaRepository<UploadedFile, Long>{
    
}
