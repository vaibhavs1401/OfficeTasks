package com.cdac.student.service;

import com.cdac.student.dao.RoleDao;
import com.cdac.student.dao.StudentDao;
import com.cdac.student.dao.UserAccountDao;
import com.cdac.student.entity.Role;
import com.cdac.student.entity.Student;
import com.cdac.student.entity.UserAccount;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private final UserAccountDao userAccountDao;
    private final RoleDao roleDao;
    private final StudentDao studentDao;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserAccountDao userAccountDao,
                                   RoleDao roleDao,
                                   StudentDao studentDao,
                                   PasswordEncoder passwordEncoder) {
        this.userAccountDao = userAccountDao;
        this.roleDao = roleDao;
        this.studentDao = studentDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long registerStudent(RegisterRequest req) {
        // 1) Uniqueness check
        if (userAccountDao.findByEmail(req.email()).isPresent()) {                                                                                                                                                          
            throw new IllegalArgumentException("Email already registered");
        }

        // 2) Fetch or create ROLE_STUDENT
        Role studentRole = roleDao.findByName(Role.RoleName.ROLE_STUDENT);
        if (studentRole == null) {
            studentRole = new Role(Role.RoleName.ROLE_STUDENT);
            studentRole = roleDao.save(studentRole);
        }

        // 3) Create UserAccount with encoded password
        UserAccount ua = new UserAccount();
        ua.setEmail(req.email());
        ua.setPassword(passwordEncoder.encode(req.password()));
        ua.getRoles().add(studentRole);
        ua = userAccountDao.save(ua); // id generated

        // 4) Create Student profile linked to the user (JoinColumn user_id)
        Student st = new Student();
        st.setAccount(ua);
        st.setName(req.name());
        st.setStandard(Objects.requireNonNullElse(req.standard(), 0));
        st.setAge(Objects.requireNonNullElse(req.age(), 0));

        String roll = req.rollNo();
        if (roll == null || roll.isBlank()) {
            roll = "STU-" + ua.getId(); // simple auto roll number; adjust as needed
        }
        st.setRollNo(roll);

        studentDao.save(st);

        return ua.getId();
    }
    
}


