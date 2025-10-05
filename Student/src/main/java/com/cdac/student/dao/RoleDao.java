package com.cdac.student.dao;

import com.cdac.student.entity.Role;

public interface RoleDao {
    Role findByName(Role.RoleName name);
    Role save(Role role);
}
