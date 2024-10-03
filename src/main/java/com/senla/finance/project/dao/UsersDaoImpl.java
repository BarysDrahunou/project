package com.senla.finance.project.dao;

import com.senla.finance.project.model.roles.Roles;
import com.senla.finance.project.model.users.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public class UsersDaoImpl implements UsersDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void merge(User user) {
        entityManager.merge(user);
    }

    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public Roles getUserRole(String login) {
        List roles =  entityManager.createQuery("SELECT u.role FROM User u WHERE u.login LIKE ?1")
                .setParameter(1, login).getResultList();
               if (roles.isEmpty()) throw new RuntimeException("Your user doesn't exist");
        return (Roles) roles.getFirst();
    }

    public List<User> findAllWithExpiringSubscription() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }
}
