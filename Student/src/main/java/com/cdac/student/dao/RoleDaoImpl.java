package com.cdac.student.dao;

import com.cdac.student.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Role findByName(Role.RoleName name) {
        TypedQuery<Role> q = em.createQuery(
                "SELECT r FROM Role r WHERE r.name = :name", Role.class);
        q.setParameter("name", name);
        List<Role> res = q.getResultList();
        return res.isEmpty() ? null : res.get(0);
    }

    @Override
    public Role save(Role role) {
        if (role.getId() == null) {
            em.persist(role);
            return role;
        } else {
            return em.merge(role);
        }
    }
}
