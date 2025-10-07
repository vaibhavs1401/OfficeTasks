package com.cdac.student.config.cmdrunner;

import com.cdac.student.dao.RoleDao;
import com.cdac.student.entity.Role;
import com.cdac.student.entity.Role.RoleName;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer {

    @Autowired
    private RoleDao roleRepository;

    @PostConstruct
    public void init() {
        System.out.println("🛠️ Role initializer running...");

        for (RoleName roleName : RoleName.values()) {
            Role existingRole = roleRepository.findByName(roleName);
            if (existingRole == null) {
                Role role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
                System.out.println("Inserted role: " + roleName.name());
            }
        }
    }
}

