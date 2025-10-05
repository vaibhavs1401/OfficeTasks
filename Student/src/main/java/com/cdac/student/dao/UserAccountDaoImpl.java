package com.cdac.student.dao;

import com.cdac.student.entity.UserAccount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserAccountDaoImpl implements UserAccountDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<UserAccount> findById(Long id) {
        return Optional.ofNullable(em.find(UserAccount.class, id));
    }

    @Override
    public Optional<UserAccount> findByEmail(String email) {
        var list = em.createQuery(
            "SELECT DISTINCT u FROM UserAccount u LEFT JOIN FETCH u.roles WHERE u.email = :email",
            UserAccount.class)
            .setParameter("email", email)
            .getResultList();
        return list.stream().findFirst();
    }


    @Override
    public List<UserAccount> findAllOrdered() {
        return em.createQuery(
                "SELECT u FROM UserAccount u ORDER BY u.email ASC", UserAccount.class)
            .getResultList();
    }

    @Override
    public UserAccount save(UserAccount user) {
        if (user.getId() == null) {
            em.persist(user);
            return user;
        }
        return em.merge(user);
    }

    @Override
    public void delete(UserAccount user) {
        var managed = em.contains(user) ? user : em.merge(user);
        em.remove(managed);
    }
}
