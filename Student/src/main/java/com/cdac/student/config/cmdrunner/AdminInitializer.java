package com.cdac.student.config.cmdrunner;

import com.cdac.student.dao.AdminDao;
import com.cdac.student.dao.RoleDao;
import com.cdac.student.dao.UserAccountDao;
import com.cdac.student.entity.Admin;
import com.cdac.student.entity.Role;
import com.cdac.student.entity.Role.RoleName;
import com.cdac.student.entity.UserAccount;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer {
    
    @Autowired
    private RoleInitializer roleInitializer;

    @Autowired
    private UserAccountDao userRepo;

    @Autowired
    private AdminDao adminRepo;

    @Autowired
    private RoleDao roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        System.out.println("🔧 Admin initializer running...");

        String defaultEmail = "admin@student.com";
        String defaultPassword = "admin123";

        if (userRepo.findByEmail(defaultEmail).isEmpty()) {
            Role adminRole = roleRepo.findByName(RoleName.ROLE_ADMIN);
            
                //.orElseThrow(() -> new RuntimeException("ROLE_ADMIN not found"));
            if(adminRole == null){
                throw new RuntimeException("ROLE_ADMIN not found!");
            }    

            UserAccount user = new UserAccount();
            user.setEmail(defaultEmail);
            user.setPassword(passwordEncoder.encode(defaultPassword));
            user.addRole(adminRole);

            user = userRepo.save(user);

            Admin admin = new Admin(user, "Default Admin");
            adminRepo.saveOrUpdate(admin);

            System.out.println("✅ Admin created with email: " + defaultEmail);
        } else {
            System.out.println("ℹ️ Admin already exists.");
        }
    }
}
