package com.senla.finance.project.dao;

import com.senla.finance.project.model.users.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.senla.finance.project.utils.Constants.CHECK_IF_USER_EXISTS_QUERY;
import static com.senla.finance.project.utils.Constants.FIND_ALL_USERS_QUERY;

@Component
@Transactional
public class UsersDaoImpl implements UsersDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void merge(User user) {
        entityManager.merge(user);
    }

    public List<User> findAll() {
        return entityManager.createQuery(FIND_ALL_USERS_QUERY).getResultList();
    }

    @Override
    public boolean checkIfUserExists(String email) {
        List users = entityManager.createQuery(CHECK_IF_USER_EXISTS_QUERY)
                .setParameter(1, email).getResultList();
        return !users.isEmpty();
    }

}