package com.cdac.student.dao;

import com.cdac.student.entity.UserAccount;

import java.util.List;
import java.util.Optional;

public interface UserAccountDao {

    Optional<UserAccount> findById(Long id);

    Optional<UserAccount> findByEmail(String email);

    List<UserAccount> findAllOrdered(); // ORDER BY email

    UserAccount save(UserAccount user);

    void delete(UserAccount user);
}
