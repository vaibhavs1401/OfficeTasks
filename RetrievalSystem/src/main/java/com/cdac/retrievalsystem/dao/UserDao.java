/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.retrievalsystem.dao;

import com.cdac.retrievalsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hcdc
 */
public interface UserDao extends JpaRepository<User, Long>{
    
}
