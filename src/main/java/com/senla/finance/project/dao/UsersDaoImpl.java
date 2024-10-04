package com.senla.finance.project.dao;

import com.senla.finance.project.model.users.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.senla.finance.project.utils.Constants.*;

@Component
@Transactional
public class UsersDaoImpl implements UsersDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void merge(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return (User) getUserByEmail(email).getFirst();
    }

    public List<User> findAll() {
        return entityManager.createQuery(FIND_ALL_USERS_QUERY).getResultList();
    }

    @Override
    public boolean checkIfUserExists(String email) {
        return !getUserByEmail(email).isEmpty();
    }

    private List getUserByEmail(String email) {
        return entityManager.createQuery(FIND_USER_BY_EMAIL_QUERY)
                .setParameter(PARAMETER_NUMBER, email).getResultList();
    }
}