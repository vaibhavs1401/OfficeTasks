/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.student.service;

import com.cdac.student.entity.UserAccount;
import java.util.Optional;

/**
 *
 * @author hcdc
 */
public interface UserAccountService {

    public boolean existsByEmail(String email);

    public String encodePassword(String password);

    public UserAccount save(UserAccount account);
    
    Optional<UserAccount> findById(Long id);
    
}
